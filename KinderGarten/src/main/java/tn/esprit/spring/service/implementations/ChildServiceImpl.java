package tn.esprit.spring.service.implementations;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Child;
import tn.esprit.spring.entity.Classroom;
import tn.esprit.spring.entity.Parent;
import tn.esprit.spring.repository.IChildRepository;
import tn.esprit.spring.repository.IClassroomRepository;
import tn.esprit.spring.repository.IKindergartenRepository;
import tn.esprit.spring.service.interfaces.IChildService;

@Service
public class ChildServiceImpl implements IChildService {
	
	@Autowired
	IChildRepository childRepo ;
	@Autowired
	IClassroomRepository classeRepo;
	@Autowired
	IKindergartenRepository kinderRepo;
	@Autowired
	EmailServiceImpl emailService;

	
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

		
	public Child affectChildtoClass(int idChild, int idClasse) {
		Classroom classe = classeRepo.findById(idClasse).get();
		Child child = childRepo.findById(idChild).get();
		Parent s = child.getParent();     //ligne1 pour mail
		Classroom c = child.getClassroom(); 
		Date d= Date.valueOf(LocalDate.now());
		if(classe.getKindergarten().getDatefinInscrit().before(d)) {
			System.out.println("impossible d inscrire");
			String to =s.getEmail();
			emailService.sendSimpleEmail(to,"Response to your registration in our kindergarten",
			"we are sorry Your child's registration for the daycare "
			+"was refused on the grounds that our daycare has closed registrations");
		}
		// test si le child est déjà affecte à une classroom
		// si child affecte à une class et le nv classe est non saturé  
		else {
			
			if(classeRepo.displayClassroomNonSaturated().contains(classe) && (c != null)){
		   // c  c'est l ancienne classroom & classe nouvelle
			c.setNbInscrit(c.getNbInscrit()-1);
			child.setClassroom(classe);
			classe.setNbInscrit(classe.getNbInscrit()+1);
			child.setDateInscrit(Date.valueOf(LocalDate.now()));
			//child.setDateInscrit(Date.valueOf(LocalDate.now()));
			childRepo.save(child);
			classeRepo.save(c);
			//System.out.println("test child affected and classe non sature"); 
			}
			else if(classe.getNbInscrit() < classe.getNbr_max())
			{
				child.setClassroom(classe);
				classe.setNbInscrit(classe.getNbInscrit()+1);
				child.setDateInscrit(Date.valueOf(LocalDate.now()));
				childRepo.save(child);
				classeRepo.save(classe);
				String to2 =s.getEmail();
				emailService.sendSimpleEmail(to2,"Response to your registration in our kindergarten","your child's registration request is accepted");
						
				}
			} 
		//if classroom saturated
		//System.out.println("classrrom saturated");
		return child;
		
		
	}
	
	@Override
	public Child deleteChildFromClasse(int idChild, int idClasse) {
		Classroom classe = classeRepo.findById(idClasse).get();
		Child c= childRepo.findById(idChild).get();
		c.setClassroom(null);
		classe.setNbInscrit(classe.getNbInscrit()-1);
		c.setDateInscrit(null);
		childRepo.save(c);
		classeRepo.save(classe);
		return c;
	}
	
}
