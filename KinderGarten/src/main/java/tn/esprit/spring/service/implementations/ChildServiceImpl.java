package tn.esprit.spring.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Child;
import tn.esprit.spring.entity.Classroom;
import tn.esprit.spring.entity.KinderGarten;
import tn.esprit.spring.repository.IChildRepository;
import tn.esprit.spring.repository.IClassroomRepository;
import tn.esprit.spring.service.interfaces.IChildService;

@Service
public class ChildServiceImpl implements IChildService {
	
	@Autowired
	IChildRepository childRepo ;
	@Autowired
	IClassroomRepository classeRepo;
	
	@Override
	public Child addChild(Child c) {
		childRepo.save(c);
		return c;
	}
	@Override
	public void deleteChild(int id) {
		childRepo.deleteById(id);
		
	}
	@Override
	public List<Child> getAllChildren() {
		return (List<Child>)  childRepo.findAll() ;
		
	}
	@Override
	public Child updateChild(Child c) {
		
		return childRepo.save(c);
	}
	@Override
	public Child getChild(int id) {
		return childRepo.findById(id).get();
	}
	
	@Override
	public Child affectChildtoClass(int idChild, int idClasse) {
		Classroom classe = classeRepo.findById(idClasse).get();
		Child child = childRepo.findById(idChild).get();
		Classroom c = child.getClassroom(); 
		// test si le child est déjà affecte à une classroom
		// si child affecte à une class et le nv classe est non saturé  
		if(classeRepo.displayClassroomNonSaturated().contains(c) && (c != null)){
			c.setNbInscrit(c.getNbInscrit()-1);
			classeRepo.save(c);
			//System.out.println("test child affected and classe non sature");
		}
		if(classe.getNbInscrit() < classe.getNbr_max())
		{
			child.setClassroom(classe);
			classe.setNbInscrit(classe.getNbInscrit()+1);
			childRepo.save(child);
			classeRepo.save(classe);
		}
		//if classroom saturated
		return child;
	}
	@Override
	public Child deleteChildFromClasse(int idChild, int idClasse) {
		Classroom classe = classeRepo.findById(idClasse).get();
		Child c= childRepo.findById(idChild).get();
		c.setClassroom(null);
		classe.setNbInscrit(classe.getNbInscrit()-1);
		childRepo.save(c);
		classeRepo.save(classe);
		return c;
	}
	
	


	
	
	

	

}
