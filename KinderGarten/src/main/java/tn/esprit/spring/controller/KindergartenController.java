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
import tn.esprit.spring.entity.KinderGarten;
import tn.esprit.spring.service.implementations.KindergartenServiceImpl;

@RestController
@RequestMapping(path = "/kindergarten")
public class KindergartenController {
	
	@Autowired
	KindergartenServiceImpl kindergartenService ;
	
		

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
}
