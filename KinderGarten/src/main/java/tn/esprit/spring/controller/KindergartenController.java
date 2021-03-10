package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import tn.esprit.spring.entity.KinderGarten;
import tn.esprit.spring.service.implementations.ClassroomServiceImpl;
import tn.esprit.spring.service.implementations.KindergartenServiceImpl;

@RestController
@RequestMapping(path = "/kindergarten")
public class KindergartenController {
	
	@Autowired
	KindergartenServiceImpl kindergartenService ;
	
	@Autowired
	ClassroomServiceImpl  classeService;
	
	
	//http://localhost:8081/kindergarten/servlet/kindergarten/retrieve-all-kindergarten
	@GetMapping("/retrieve-all-kindergarten")
	@ResponseBody
	public List<KinderGarten> getAllKindergarten()
	{
		List<KinderGarten> list = kindergartenService.getAllkindergarten();
		return list;
	}
	
	//http://localhost:8081/kindergarten/servlet/kindergarten/add-kindergarten
	@PostMapping("/add-kindergarten")
	@ResponseBody
	public KinderGarten addKindergarten(@RequestBody KinderGarten kindergarten)
	{
		KinderGarten kindergart = kindergartenService.addKindergarten(kindergarten);
		return kindergart;
		
	}
	

	//http://localhost:8081/kindergarten/servlet/kindergarten/update-kindergarten
	@PutMapping("/update-kindergarten")
	@ResponseBody
	public KinderGarten updateKindergarten(@RequestBody KinderGarten kindergarten)
	{
	KinderGarten kindergart = kindergartenService.addOrUpdateKindergarten(kindergarten);
	return  kindergart ;
    }
	
	//http://localhost:8081/kindergarten/servlet/kindergarten/remove-kindergarten/{user-id}
		@DeleteMapping("/remove-kindergarten/{kinder-id}")
		@ResponseBody
		public void removeKindergarten(@PathVariable("kinder-id") int id) 
		{
			kindergartenService.deleteKindergartenById(id);
		}
	
		//crud entite Classroom
		
		//http://localhost:8081/kindergarten/servlet/kindergarten/retrieve-all-classe
		@GetMapping("/retrieve-all-classe")
		@ResponseBody
		public List<Classroom> getAllclasse()
		{
			List<Classroom> list = classeService.getAllClass();
			return list;
		}
		
		//http://localhost:8081/kindergarten/servlet/kindergarten/add-classe
		@PostMapping("/add-classe")
		@ResponseBody
		public Classroom addClasse(@RequestBody Classroom classe)
		{
			Classroom c = classeService.addClass(classe);
			return c ;
		}
		//http://localhost:8081/kindergarten/servlet/kindergarten/update-classe
		@PutMapping("/update-classe")
		@ResponseBody
		public Classroom updateClasse(@RequestBody Classroom classe)
		{
		Classroom c = classeService.addOrUpdateClass(classe);
		return  c ;
	    }
		
		//http://localhost:8081/kindergarten/servlet/kindergarten/remove-classe/{classe-id}
				@DeleteMapping("/remove-classe/{classe-id}")
				@ResponseBody
				public void removeClasse(@PathVariable("classe-id") int id) 
				{
					classeService.deleteClassById(id);
				}
				
				
	  //http://localhost:8081/kindergarten/servlet/kindergarten/retrieve-kindergardenBy/{name}
	  @GetMapping("/retrieve-kindergardenBy/{name}")			
	  public List<KinderGarten> getKinderGartenByName(@PathVariable String name){
		  List<KinderGarten> k = kindergartenService.getKinderGartenByName(name);
		  return k;
	  }
	  
	//http://localhost:8081/kindergarten/servlet/kindergarten/retrieve-kindergardenByPlace/{place}
	  @GetMapping("/retrieve-kindergardenByPlace/{place}")			
	  public List<KinderGarten> getKinderGartenByPlace(@PathVariable String place){
		  List<KinderGarten> p = kindergartenService.getKinderGartenByPlace(place);
		  return p;
	  }
	  
	  // method for entity classroom
	  
	//http://localhost:8081/kindergarten/servlet/kindergarten/classroom/sature
	  @GetMapping("/classroom/sature")
	  public List<Classroom> dispalyClassroomSaturated() {
			return classeService.dispalyClassroomSaturated();
		}
        

		//http://localhost:8081/kindergarten/servlet/kindergarten/classroom/Nsature
		@GetMapping("/classroom/Nsature")
		public List<Classroom> displayDaycareNonSaturated() {
			return classeService.displayClassroomNonSaturated();
		}
	}