package tn.esprit.spring.service.interfaces;

import java.util.List;

import tn.esprit.spring.entity.Classroom;

public interface IClassroomService {

	   public List<Classroom> getAllClass();

	   public  Classroom addClass(Classroom classe);
		
	   public void deleteClassById(int id);
		
	   public Classroom addOrUpdateClass(Classroom kindergarten); 
		
	   public List<Classroom> dispalyClassroomSaturated();
		
       public List<Classroom> displayClassroomNonSaturated();
       public String affecterClassesToKinderG(int idclasse, int idkinderG);
}
