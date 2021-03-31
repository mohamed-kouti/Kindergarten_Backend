package tn.esprit.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Complaint_kinder;
import tn.esprit.spring.repository.IComplaint_kinderRepository;
import tn.esprit.spring.service.implementations.Complaint_kinderServiceImpl;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

	@Autowired
	IComplaint_kinderRepository iComplaint_kinderRepository;
	
	@Autowired
	Complaint_kinderServiceImpl complaint_kinderServiceImpl;


	@GetMapping("")
	public ResponseEntity<List<Complaint_kinder>> index() {
		try {
			List<Complaint_kinder> complaint_kinders = new ArrayList<Complaint_kinder>();

			iComplaint_kinderRepository.findAll().forEach(complaint_kinders::add);

			if (complaint_kinders.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(complaint_kinders, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Complaint_kinder> getTutorialById(@PathVariable("id") int id) {
		Optional<Complaint_kinder> tutorialData = iComplaint_kinderRepository.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	public ResponseEntity<Complaint_kinder> add(@RequestBody Complaint_kinder complaint_kinder) {
		try {
			complaint_kinder.setStat(false);
			Complaint_kinder _Complaint_kinder = iComplaint_kinderRepository.save(complaint_kinder);
			
			complaint_kinderServiceImpl.banKinderGarten(_Complaint_kinder.getGarten());
			return new ResponseEntity<>(_Complaint_kinder, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Complaint_kinder> updateTutorial(@PathVariable("id") int id,
			@RequestBody Complaint_kinder complaint_kinder) {
	
		Optional<Complaint_kinder> complaint_kinderData = iComplaint_kinderRepository.findById(id);

		if (complaint_kinderData.isPresent()) {
			Complaint_kinder _Complaint_kinder = complaint_kinderData.get();
			_Complaint_kinder.setDate_comp(new Date());
			_Complaint_kinder.setGarten(complaint_kinder.getGarten());
			_Complaint_kinder.setParent(complaint_kinder.getParent());
			_Complaint_kinder.setMsg(complaint_kinder.getMsg());
			_Complaint_kinder.setStat(false);
			return new ResponseEntity<>(iComplaint_kinderRepository.save(_Complaint_kinder), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") int id) {
		try {
			iComplaint_kinderRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

