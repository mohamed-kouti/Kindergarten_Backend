package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.entity.ComplainsByKinderGarten;
import tn.esprit.spring.entity.ComplainsByParent;
import tn.esprit.spring.entity.ComplainsByPost;
import tn.esprit.spring.repository.IComplaint_kinderRepository;

@RestController
@RequestMapping(path = "/report")
public class ReportController {
	
	@Autowired
	IComplaint_kinderRepository iComplaint_kinderRepository;
	
	@GetMapping("/complaint")
	public ResponseEntity<List<ComplainsByKinderGarten>> complaintByKinderGarten() {
		try {
			List<ComplainsByKinderGarten> complainsByKinderGarten = iComplaint_kinderRepository.join();


			if (complainsByKinderGarten.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(complainsByKinderGarten, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/complaint/post")
	public ResponseEntity<List<ComplainsByPost>> complaintByPost() {
		try {
			List<ComplainsByPost> complainsByPost = iComplaint_kinderRepository.complaintByPost();


			if (complainsByPost.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(complainsByPost, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

	@GetMapping("/complaint/parent")
	public ResponseEntity<List<ComplainsByParent>> complaintParent() {
		try {
			List<ComplainsByParent> complainsByParent = iComplaint_kinderRepository.complaintByParent();


			if (complainsByParent.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(complainsByParent, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

