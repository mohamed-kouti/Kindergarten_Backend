package tn.esprit.spring.service.interfaces;

import java.util.List;

import tn.esprit.spring.entity.Child;

public interface IChildService {
	

	public Child addChild(Child c);
	public void deleteChild(int id);
	public List<Child> getAllChildren();
	public Child getChild(int id);
	public Child updateChild(Child c) ;
	public Child affectChildtoClass(int idChild, int idClasse);
	public Child deleteChildFromClasse(int idChild,int idClasse);
	

}