package tn.esprit.spring.service.implementations;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Classroom;
import tn.esprit.spring.entity.KinderGarten;
import tn.esprit.spring.repository.IClassroomRepository;
import tn.esprit.spring.repository.IKindergartenRepository;
import tn.esprit.spring.service.interfaces.IClassroomService;

@Service
public class ClassroomServiceImpl implements IClassroomService {


	@Autowired
	IClassroomRepository classeRepo;
	
	@Autowired
	IKindergartenRepository kinderRepo;
	
	
	
	
	@Override
	public List<Classroom> getAllClass() {
		return (List<Classroom>)  classeRepo.findAll() ;	
		
	}


	@Override
	public Classroom addClass(Classroom classe) {
		classeRepo.save(classe);
		return classe;
	 }

	
   @Override
	public void deleteClassById(int id) {
		classeRepo.deleteById(id);
		
	}


	@Override
	public Classroom addOrUpdateClass(Classroom classe) {
		Classroom c =classeRepo.save(classe);
		return c;
	}


	@Override
	public List<Classroom> dispalyClassroomSaturated() {
		return classeRepo.dispalyClassroomSaturated();
	}


	@Override
	public List<Classroom> displayClassroomNonSaturated() {
		return classeRepo.displayClassroomNonSaturated();
	}

    // Affect classes to a kindergarten
	@Override
	public String affecterClassesToKinderG(int idclasse, int idkinderG) {
		// TODO Auto-generated method stub
		Classroom c = classeRepo.findById(idclasse).get();
		KinderGarten kinder= kinderRepo.findById(idkinderG).get();
		c.setKindergarten(kinder);
		classeRepo.save(c);
		kinderRepo.save(kinder);
		return "Classroom affected succesfully to kindergarten";
		
		}


	@Override
	public Classroom addClassroom(Classroom classes) {
		if(classes.getDateEnd().before(classes.getDatebegin())) {
			System.out.println("can't add classroom because datebegin is after the date end");
		} 
		else {
			long p = Math.round((classes.getDateEnd().getTime() - classes.getDatebegin().getTime()) / (double) 86400000);
			classes.setPeriode(p);
			if(p < 30) 
			{ classes.setPrice_T(classes.getPrice_M());}
			else if( p>30 && p<60)
			{ classes.setPrice_T(classes.getPrice_M() - (classes.getPrice_M() * 0.1));} 
			else if( p>60 && p<90) 
			{ classes.setPrice_T(classes.getPrice_M() - (classes.getPrice_M() * 0.2));}
			else if( p>90 && p<120)
			{ classes.setPrice_T(classes.getPrice_M() - (classes.getPrice_M() * 0.3));}
			classeRepo.save(classes);
			}
			return classes;
		}


	@Override
	public Double RevenuePerYear(String year) {
		Double total = 0.0;
		List<Classroom> l = classeRepo.classroomRevenueYear(year);
		for(Classroom c: l) {
			total += c.getNbInscrit() * c.getPrice_T();
		}
		return total;
	}

	}
	
	

	
