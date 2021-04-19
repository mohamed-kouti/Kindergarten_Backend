package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.entity.Classroom;
import tn.esprit.spring.service.implementations.ChildServiceImpl;
import tn.esprit.spring.service.implementations.ClassroomServiceImpl;
import tn.esprit.spring.service.implementations.KindergartenServiceImpl;
@Secured({ "ROLE_PARENT", "ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
@RestController
@RequestMapping(path = "/classroom")
public class ClassroomController {
	
	@Autowired
	KindergartenServiceImpl kindergartenService ;
	
	@Autowired
	ClassroomServiceImpl  classeService;
	@Autowired
	ChildServiceImpl childService;
	
	
	//crud entite Classroom
	
			//http://localhost:8081/kindergarten/servlet/classroom/retrieve-all-classe
			@GetMapping("/retrieve-all-classe")
			@ResponseBody
			public List<Classroom> getAllclasse()
			{
				List<Classroom> list = classeService.getAllClass();
				return list;
			}
			
			//http://localhost:8081/kindergarten/servlet/classroom/add-classe
			@PostMapping("/add-classe")
			@ResponseBody
			public Classroom addClasse(@RequestBody Classroom classe)
			{
				Classroom c = classeService.addClassroom(classe);
				return c ;
			}
			//http://localhost:8081/kindergarten/servlet/classroom/update/{id}
			@PutMapping("/update/{id}")
			public Classroom updateClasse(@PathVariable int id, @RequestBody Classroom c) {
				return classeService.updateClassroom(id, c);
			}
			
			//http://localhost:8081/kindergarten/servlet/classroom/update-classe
			@PutMapping("/update-classe")
			@ResponseBody
			public Classroom updateClasse(@RequestBody Classroom classe)
			{
			Classroom c = classeService.addOrUpdateClass(classe);
			return  c ;
		    }
			
			//http://localhost:8081/kindergarten/servlet/classroom/remove-classe/{classe-id}
					@DeleteMapping("/remove-classe/{classe-id}")
					@ResponseBody
					public void removeClasse(@PathVariable("classe-id") int id) 
					{
						classeService.deleteClassById(id);
					}
					
			// method for entity classroom
					  
			//http://localhost:8081/kindergarten/servlet/classroom/classroom/sature
			 @GetMapping("/classroom/sature")
		     public List<Classroom> dispalyClassroomSaturated() {
				return classeService.dispalyClassroomSaturated();
						}
		   //http://localhost:8081/kindergarten/servlet/classroom/classroom/Nsature
			@GetMapping("/classroom/Nsature")
			public List<Classroom> displayDaycareNonSaturated() {
				return classeService.displayClassroomNonSaturated();
						}
						
			//http://localhost:8081/kindergarten/servlet/classroom/affectToKinderG/{idclasse}/{idkinder}
			@PutMapping("/affectToKinderG/{idclasse}/{idkinder}")  
			 public String affectClassTokKinderG(@PathVariable("idclasse")int idclasse,@PathVariable("idkinder")int idkinder)   
			  {  
				
	         return classeService.affecterClassesToKinderG(idclasse, idkinder);
			  }
		
			
			//http://localhost:8081/kindergarten/servlet/classroom/static/revenue/{year}
			@GetMapping("/static/revenue/{year}")
			public Double classesRevenuePerYear(@PathVariable("year") String year){
				return classeService.RevenuePerYear(year);
			}
			
			//http://localhost:8081/kindergarten/servlet/classroom/Classrrom/displayByDate
			@GetMapping("/Classrrom/displayByDate")
			public List<Classroom> displayClassroomByDate(){
				return classeService.displayClassroomByDate();
			}
			
		
		
		  

}
