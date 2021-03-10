package tn.esprit.spring.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Classroom;
import tn.esprit.spring.repository.IClassroomRepository;
import tn.esprit.spring.service.interfaces.IClassroomService;

@Service
public class ClassroomServiceImpl implements IClassroomService {


	@Autowired
	IClassroomRepository classeRepo;
	

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

	
}
