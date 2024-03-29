package tn.esprit.spring.service.interfaces;

import java.util.List;

import tn.esprit.spring.entity.Child;
import tn.esprit.spring.entity.Classroom;
import tn.esprit.spring.entity.KinderGarten;

public interface IClassroomService {

	   public List<Classroom> getAllClass();

	   public  Classroom addClass(Classroom classe);
		
	   public void deleteClassById(int id);
		
	   public Classroom addOrUpdateClass(Classroom kindergarten); 
		
	   public List<Classroom> dispalyClassroomSaturated();
		
       public List<Classroom> displayClassroomNonSaturated();
       //public String affecterClassesToKinderG(int idclasse, int idkinderG);
       //public Classroom affecterClassesToKinderG(int idclasse, int idkinderG);
       public String affecterClassesToKinderG(int idclasse, int idkinderG);
       
       public Classroom addClassroom(Classroom classes) ;
       //,int idkinderG
       public Double RevenuePerYear(String year);
       
       public List<Classroom> displayClassroomByDate();
       public Classroom updateClassroom(int id, Classroom c);
       public Classroom getClasseById(int id);
       
       public List<Classroom> dispalayUnaffectedClass();
       
       
    	//public List<Classroom> findClassroomByKinderG(KinderGarten k);
       
       //public Classroom getClasseByChildAge(Child c);

	//public Classroom getClasseByChildAge(List<Child> kids);
       

//public Classe getClasseByKidAge(Child c,KinderGarten kinder)
}
