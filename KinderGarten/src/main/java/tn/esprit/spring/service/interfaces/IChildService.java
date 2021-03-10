package tn.esprit.spring.service.interfaces;

import java.util.List;

import tn.esprit.spring.entity.Child;

public interface IChildService {
	

	Child addChild(Child c);
	void deleteChild(int id);
	List<Child> getAllChildren();
	Child updateChild(int id, Child c);
	Child getChild(int id);

}