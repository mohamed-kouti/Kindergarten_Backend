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
import tn.esprit.spring.service.implementations.ChildServiceImpl;
import tn.esprit.spring.service.implementations.ClassroomServiceImpl;
import tn.esprit.spring.service.implementations.KindergartenServiceImpl;


@RestController
@RequestMapping(path = "/child")
public class ChildController {
	@Autowired
	KindergartenServiceImpl kindergartenService ;
	
	@Autowired
	ClassroomServiceImpl  classeService;
	@Autowired
	ChildServiceImpl childService;

	 // crud entity Child
	
	//http://localhost:8081/kindergarten/servlet/child/add-child
	@PostMapping("/add-child")
	public Child addChild(@RequestBody Child c){
		return childService.addChild(c);
	}
	
	//http://localhost:8081/kindergarten/servlet/child/update-child
	@PutMapping("/update-child")
	@ResponseBody
	public Child updateChild(@RequestBody Child c)
	{
		return childService.updateChild(c);
	}
	

	//http://localhost:8081/kindergarten/servlet/child/getAll
	@GetMapping("/getAll")
	public List<Child> getAllChild(){
		return childService.getAllChildren();
	}
	
	//http://localhost:8081/kindergarten/servlet/child/getBy/{idChild}
	@GetMapping("/getBy/{idChild}")
	public Child getChildById(@PathVariable("idChild") int id){
		return childService.getChild(id);
	}
	//http://localhost:8081/kindergarten/servlet/child/del/{idChild}
	@DeleteMapping("/del/{idChild}")
	public void deleteChild(@PathVariable("idChild") int id) {
		childService.deleteChild(id);
	}

	//http://localhost:8081/kindergarten/servlet/child/affToClass{idChild}/{idClasse}"
	@PutMapping("/affToClass/{idChild}/{idClasse}")
	public Child affectChildToClass(@PathVariable("idChild") int idChild, @PathVariable("idClasse") int idClasse){
		return childService.affectChildtoClass(idChild, idClasse);
	}
	
	//http://localhost:8081/kindergarten/servlet/child/removeFromClass/{idChild}/{idClasse}
	@PutMapping("/removeFromClass/{idChild}/{idClasse}")
	public Child removeChildFromClasse(@PathVariable("idChild") int idChild,@PathVariable("idClasse") int idClasse){
		return childService.deleteChildFromClasse(idChild, idClasse);
	}
	
}
