package tn.esprit.spring.service.interfaces;

import tn.esprit.spring.entity.Admin;
import tn.esprit.spring.entity.KinderGarten_owner;
import tn.esprit.spring.entity.Parent;
import tn.esprit.spring.entity.User;

public interface IUserService {
	public void addPrent (Parent p);
	
	public void addKindergarten (KinderGarten_owner k);
	
	public void addAdmin(Admin a);

}
