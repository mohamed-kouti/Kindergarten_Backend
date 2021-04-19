package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Child;
import tn.esprit.spring.entity.Parent;
import tn.esprit.spring.repository.IChildRepository;
import tn.esprit.spring.repository.IParentRepository;
import tn.esprit.spring.repository.IUserRepository;
import tn.esprit.spring.service.implementations.ParentServiceImpl;
@Secured({ "ROLE_PARENT", "ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
@RestController
@RequestMapping("/parent")
public class ParentController {
	@Autowired
	ParentServiceImpl parentService;
	@Autowired
	IChildRepository childRepo;
	@Autowired
	IUserRepository userRepo;
	@Autowired
	IParentRepository parentRepo;
     
	//parent add your child
	//http://localhost:8081/kindergarten/servlet/parent/addChildToParent/{idParent}
	@PostMapping("/addChildToParent/{idParent}")
	public Parent addChildToParent(@RequestBody Child c,@PathVariable("idParent") int idParent) {
		Parent p = parentRepo.findById(idParent).get();
		if(p==null) 
		//{ throw new RuntimeException("you are not a parent"); }
		System.out.println("you are not a parent");
		c.setParent(p);
		p.getChilds().add(c);
		return parentRepo.save(p);
	}
	
	//http://localhost:8081/kindergarten/servlet/parent/add-parent
	@PostMapping("/add-parent")
	public Parent addParent(@RequestBody Parent p){
		return parentService.addParent(p);
	}
	
	//http://localhost:8081/kindergarten/servlet/kindergarten/parent/update-parent
	@PutMapping("/update-parent")
	@ResponseBody
	public Parent updateParent(@RequestBody Parent p)
	{
		return parentService.updateParent(p);
	}
	

}
