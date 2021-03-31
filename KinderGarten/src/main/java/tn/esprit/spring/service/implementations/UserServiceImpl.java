package tn.esprit.spring.service.implementations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Parent;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.IUserRepository;
import tn.esprit.spring.service.interfaces.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserRepository userrep;

	@Override
	public void addPrent(Parent p) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		String ep = pe.encode(p.getPassword());
		p.setPassword(ep);
		userrep.save(p);

	}

	@Override
	public List<User> findAll() {
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
	public User getUserById(int id) {
		return userrep.findById(id).get();
	}
}
