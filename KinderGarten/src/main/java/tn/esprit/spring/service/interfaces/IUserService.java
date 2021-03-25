package tn.esprit.spring.service.interfaces;

import java.util.List;
import java.util.Set;

import tn.esprit.spring.entity.Parent;
import tn.esprit.spring.entity.User;

public interface IUserService {

	void addPrent(Parent p);

	List<User> findAll();

	User findByUserName(String userName);

	Set<String> findAllByName();

}
