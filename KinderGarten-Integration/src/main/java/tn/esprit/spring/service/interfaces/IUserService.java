package tn.esprit.spring.service.interfaces;

import java.util.List;
import java.util.Set;

import tn.esprit.spring.entity.Admin;
import tn.esprit.spring.entity.KinderGarten_owner;
import tn.esprit.spring.entity.Parent;
import tn.esprit.spring.entity.User;

public interface IUserService {

	User findByUserName(String userName);

	Set<String> findAllByName();

	public void addPrent(Parent p);

	public List<User> getAllUsers();

	public void deleteUser(int id);

	public void updateParent(Parent p);

	public List<User> getAllParents();

	public List<User> getAllAdmins();

	public void addKindergarten(KinderGarten_owner k);

	public void updateKgowner(KinderGarten_owner k);

	public void addAdmin(Admin a);

	public void updateAdmin(Admin a);

	void BlockUser(int id);

	void ActifUser(int id);

	User getUserById(int id);

	User getUserByLogin(String mail, String pass);

}
