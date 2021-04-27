package tn.esprit.spring.service.implementations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Admin;
import tn.esprit.spring.entity.KinderGarten_owner;
import tn.esprit.spring.entity.Parent;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.IUserRepository;
import tn.esprit.spring.service.interfaces.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserRepository userrep;
	@Autowired
	public JavaMailSender emailSender;

	@Override
	public List<User> getAllUsers() {
		List<User> listUsers = (List) userrep.findAll();
		return listUsers;
	}

	@Override
	public User findByUserName(String userName) {
		User user = userrep.findByFname(userName);
		return user;
	}

	@Override
	public Set<String> findAllByName() {
		List<User> listUsers = (List) userrep.findAll();
		Set<String> response = new HashSet<String>();
		listUsers.forEach(user -> {
			String name = user.getFname();
			response.add(name);
		});

		return response;
	}

	@Override
	public void addPrent(Parent p) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		String ep = pe.encode(p.getPassword());
		p.setPassword(ep);
		userrep.save(p);

	}

	@Override
	public void addKindergarten(KinderGarten_owner k) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		String ep = pe.encode(k.getPassword());
		k.setPassword(ep);
		userrep.save(k);

	}

	@Override
	public void addAdmin(Admin a) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		String ep = pe.encode(a.getPassword());
		a.setPassword(ep);
		userrep.save(a);
	}

	@Override
	public void deleteUser(int id) {
		userrep.deleteById(id);

	}

	@Override
	public void updateParent(Parent p) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		String ep = pe.encode(p.getPassword());
		p.setPassword(ep);
		userrep.save(p);
	}

	@Override
	public void updateKgowner(KinderGarten_owner k) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		String ep = pe.encode(k.getPassword());
		k.setPassword(ep);
		userrep.save(k);
	}

	@Override
	public void updateAdmin(Admin a) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		String ep = pe.encode(a.getPassword());
		a.setPassword(ep);
		userrep.save(a);

	}

	@Override
	public List<User> getAllParents() {
		return (List<User>) userrep.getAllParent();

	}
	
	
	@Override
	public List<User> getAllDisabled() {
		return (List<User>) userrep.getAllInactive();

	}

	@Override
	public List<User> getAllAdmins() {
		return (List<User>) userrep.getAllAdmin();
	}
	
	@Override
	public List<User> getAllKdowners() {
		return (List<User>) userrep.getAllkdowner();
	}

	@Override
	public void BlockUser(int id) {
		userrep.blockUser(id);
		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(this.getUserById(id).getEmail());
		message.setSubject("ALERT USER");
		message.setText("Hello " + this.getUserById(id).getFname()
				+ ", your account is blocked.\n Contact us for more information.");

		// Send Message!
		this.emailSender.send(message);

	}

	@Override
	public void ActifUser(int id) {
		userrep.actifUser(id);
		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(this.getUserById(id).getEmail());
		message.setSubject("ACTIVATION USER");
		message.setText("Hello " + this.getUserById(id).getFname() + ", your account is Activated.");

		// Send Message!
		this.emailSender.send(message);

	}

	@Override
	public User getUserById(int id) {
		return userrep.findById(id).get();
	}
	
	@Override
	public User getUserByLogin(String mail, String pass) {
		User u = userrep.findByEmail(mail).get();
		BCryptPasswordEncoder bp = new BCryptPasswordEncoder();
		if (bp.matches(pass, u.getPassword())) {
			return u;
		}
		return null;
	}
	
	@Override
	public String getRole (String mail) {
		return userrep.getRole(mail);
	}
	
	@Override
	public int getnbrUser() {
		return userrep.getnbrUser();
	}
}
