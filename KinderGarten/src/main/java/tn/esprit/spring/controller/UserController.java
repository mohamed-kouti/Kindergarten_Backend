package tn.esprit.spring.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.ComplaintCategory;
import tn.esprit.spring.entity.Complaint_kinder;
import tn.esprit.spring.entity.Complaint_post;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.IComplaint_kinderRepository;
import tn.esprit.spring.repository.IComplaint_postRepository;
import tn.esprit.spring.repository.IUserRepository;

@RestController
@RequestMapping(path = "/ad")
public class UserController {
	
	@Autowired
	IUserRepository iUserRepository;
	
	@Autowired
	IComplaint_kinderRepository iComplaint_kinderRepository;
	
	@Autowired
	IComplaint_postRepository iComplaint_postRepository;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> index() {
		try {
			List<User> users = new ArrayList<User>();

			iUserRepository.findAll().forEach(users::add);

			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getById(@PathVariable("id") int id) {
		Optional<User> user = iUserRepository.findById(id);

		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/ban/{stat}")
	public ResponseEntity<User> ban(@RequestBody User user, @PathVariable("stat") boolean stat) {
		try {
			
			Optional<User> dataUser = iUserRepository.findById(user.getId());
			User _user = dataUser.get();
			_user.setBanned(stat);
			iUserRepository.save(_user);
			
			return new ResponseEntity<>(_user, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/complaint/moderate/{stat}")
	public ResponseEntity<Complaint_kinder> moderateComplaint(@RequestBody Complaint_kinder complaint_kinder, @PathVariable("stat") boolean stat) {
		try {
			Optional<Complaint_kinder> dataComplaint_kinder = iComplaint_kinderRepository.findById(complaint_kinder.getId());
			Complaint_kinder _complaint_kinder = dataComplaint_kinder.get();
			_complaint_kinder.setStat(stat);
			iComplaint_kinderRepository.save(_complaint_kinder);
			
			return new ResponseEntity<>(_complaint_kinder, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/complaint_post/moderate/{stat}")
	public ResponseEntity<Complaint_post> moderateComplaintPost(@RequestBody Complaint_post complaint_post, @PathVariable("stat") boolean stat) {
		try {
			Optional<Complaint_post> dataComplaint_post = iComplaint_postRepository.findById(complaint_post.getId());
			Complaint_post _complaint_post = dataComplaint_post.get();
			_complaint_post.setStat(stat);
			iComplaint_postRepository.save(_complaint_post);
			
			return new ResponseEntity<>(_complaint_post, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	
	
	@PutMapping("/complaint/catgorize/{category}")
	public ResponseEntity<Complaint_kinder> categorizeComplaintPost(@RequestBody Complaint_kinder complaint_kinder, @PathVariable("category") String category) {
		try {
			Optional<Complaint_kinder> dataComplaint_kinder = iComplaint_kinderRepository.findById(complaint_kinder.getId());
			Complaint_kinder _complaint_kinder = dataComplaint_kinder.get();
			_complaint_kinder.setComplaintCategory(ComplaintCategory.valueOf(category));
			iComplaint_kinderRepository.save(_complaint_kinder);
			
			return new ResponseEntity<>(_complaint_kinder, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}


