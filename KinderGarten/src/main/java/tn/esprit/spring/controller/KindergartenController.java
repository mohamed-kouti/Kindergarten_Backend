


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


import tn.esprit.spring.entity.Child;
import tn.esprit.spring.entity.Classroom;
import tn.esprit.spring.entity.KinderGarten;
import tn.esprit.spring.service.implementations.ChildServiceImpl;
import tn.esprit.spring.service.implementations.ClassroomServiceImpl;
import tn.esprit.spring.service.implementations.KindergartenServiceImpl;

@RestController
@RequestMapping(path = "/kindergarten")
public class KindergartenController {
	
	@Autowired
	KindergartenServiceImpl kindergartenService ;
	
	@Autowired
	ClassroomServiceImpl  classeService;
	@Autowired
	ChildServiceImpl childService;
	
	
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
		
		//Search kindergarten with name and place
		
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
			Classroom c = classeService.addClassroom(classe);
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
		
		  // crud entity Child
		
		//http://localhost:8081/kindergarten/servlet/kindergarten/child/add-child
		@PostMapping("/child/add-child")
		public Child addChild(@RequestBody Child c){
			return childService.addChild(c);
		}
		
		//http://localhost:8081/kindergarten/servlet/kindergarten/child/update-child
		@PutMapping("/child/update-child")
		@ResponseBody
		public Child updateChild(@RequestBody Child c)
		{
			return childService.updateChild(c);
		}
		

		//http://localhost:8081/kindergarten/servlet/kindergarten/child/getAll
		@GetMapping("/child/getAll")
		public List<Child> getAllChild(){
			return childService.getAllChildren();
		}
		
		//http://localhost:8081/kindergarten/servlet/kindergarten/child/getBy/{idChild}
		@GetMapping("/child/getBy/{idChild}")
		public Child getChildById(@PathVariable("idChild") int id){
			return childService.getChild(id);
		}
		//http://localhost:8081/kindergarten/servlet/kindergarten/child/del/{idChild}
		@DeleteMapping("/child/del/{idChild}")
		public void deleteChild(@PathVariable("idChild") int id) {
			childService.deleteChild(id);
		}
	
		//http://localhost:8081/kindergarten/servlet/kindergarten/child/classroom/aff/{idChild}/{idClasse}
		@PutMapping("/child/classroom/aff/{idChild}/{idClasse}")
		public Child affectChildToClass(@PathVariable("idChild") int idChild, @PathVariable("idClasse") int idClasse){
			return childService.affectChildtoClass(idChild, idClasse);
		}
		
		//http://localhost:8081/kindergarten/servlet/kindergarten/child/classroom/delete/{idChild}/{idClasse}
		@PutMapping("/child/classroom/delete/{idChild}/{idClasse}")
		public Child deleteChildFromClasse(@PathVariable("idChild") int idChild,@PathVariable("idClasse") int idClasse){
			return childService.deleteChildFromClasse(idChild, idClasse);
		}
	    //http://localhost:8081/kindergarten/servlet/kindergarten/kinderG/retrieve-kinderG-ById/{id}
		@GetMapping("/kinderG/retrieve-kinderG-ById/{id}")
		public KinderGarten getKinderGById(@PathVariable("id") int id) {
			KinderGarten k = kindergartenService.getKinderGById(id);
			 return k;
		}
		//http://localhost:8081/kindergarten/servlet/kindergarten/kinderG/displaybestKinderGByViews
		@GetMapping("/kinderG/displaybestKinderGByViews")
		public List<String> displaybestEventsByViews(){
			return kindergartenService.displayBestKinderGartensByViews();
			}
		//http://localhost:8081/kindergarten/servlet/kindergarten/classroom/affectToKinderG/{idclasse}/{idkinder}
		@PutMapping("/classroom/affectToKinderG/{idclasse}/{idkinder}")  
		 private String affectClassTokKinderG(@PathVariable("idclasse")int idclasse,@PathVariable("idkinder")int idkinder)   
		  {  
			return classeService.affecterClassesToKinderG(idclasse, idkinder);
		  }
		
		//http://localhost:8081/kindergarten/servlet/kindergarten/classroom/static/revenue/{year}
		@GetMapping("classroom/static/revenue/{year}")
		public Double classesRevenuePerYear(@PathVariable("year") String year){
			return classeService.RevenuePerYear(year);
		}
		//http://localhost:8081/kindergarten/servlet/kindergarten/kinderG/getListClasses/{id}
		@GetMapping("/kinderG/getListClasses/{id}")
		public List<Classroom> getListClasses(@PathVariable("id") int id){
			return kindergartenService.getClassesByKinderg(id);
		}
		//http://localhost:8081/kindergarten/servlet/kindergarten/kinderG/{long}/{lati}/{rayon}
		@GetMapping("/kinderG/{long}/{lati}/{rayon}")
		public List<KinderGarten> chercherParzone(@PathVariable("long")Double longi,@PathVariable("lati") Double lat,@PathVariable("rayon")Double rayon){
			return kindergartenService.chercherParZone(longi, lat, rayon);
		}
			
}