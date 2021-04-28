


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
import tn.esprit.spring.entity.KinderGarten;
import tn.esprit.spring.service.implementations.ChildServiceImpl;
import tn.esprit.spring.service.implementations.ClassroomServiceImpl;
import tn.esprit.spring.service.implementations.KindergartenServiceImpl;
@Secured({ "ROLE_PARENT", "ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
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
	
	//http://localhost:8081/kindergarten/servlet/kindergarten/update/{id}
	@PutMapping("/update/{id}")
	public KinderGarten updatekinderg(@PathVariable int id, @RequestBody KinderGarten k) {
		kindergartenService.updateKindergById(id,k);
		return k;
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
         //http://localhost:8081/kindergarten/servlet/kindergarten/kinderG/retrieveById/{id}
	     @GetMapping("/kinderG/retrieveById/{id}")
         public KinderGarten getKindergartenById(@PathVariable("id") int id){
        	 KinderGarten k= kindergartenService.getkindergartenById(id);
        	 return k;
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
		
		/*//http://localhost:8081/kindergarten/servlet/kindergarten/Classroom/displayClassroomByKinder
		@GetMapping("/Classroom/displayClassroomByKinder/{k}")
		public List<Classroom> displayClassroomByKinder(KinderGarten k){
			return classeService.findClassroomByKinderG(k);
		}*/
		}

