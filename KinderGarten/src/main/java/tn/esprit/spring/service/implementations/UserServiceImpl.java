package tn.esprit.spring.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Override
	public void addPrent(Parent p) {
		BCryptPasswordEncoder pe =new BCryptPasswordEncoder();
		String ep = pe.encode(p.getPassword());
		p.setPassword(ep);
		userrep.save(p);
		
	}

	@Override
	public void addKindergarten(KinderGarten_owner k) {
		BCryptPasswordEncoder pe =new BCryptPasswordEncoder();
		String ep = pe.encode(k.getPassword());
		k.setPassword(ep);
		userrep.save(k);
		
		
	}

	@Override
	public void addAdmin(Admin a) {
		BCryptPasswordEncoder pe =new BCryptPasswordEncoder();
		String ep = pe.encode(a.getPassword());
		a.setPassword(ep);
		userrep.save(a);
	}
	

}
