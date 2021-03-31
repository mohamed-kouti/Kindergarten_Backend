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

import tn.esprit.spring.entity.Complaint_post;
import tn.esprit.spring.repository.IComplaint_postRepository;

@RestController
@RequestMapping("/complaint/post")
public class ComplaintPostController {

	@Autowired
	IComplaint_postRepository iComplaint_postRepository;

	@GetMapping("")
	public ResponseEntity<List<Complaint_post>> index() {
		try {
			List<Complaint_post> complaint_post = new ArrayList<Complaint_post>();

			iComplaint_postRepository.findAll().forEach(complaint_post::add);

			if (complaint_post.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(complaint_post, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Complaint_post> getTutorialById(@PathVariable("id") int id) {
		Optional<Complaint_post> tutorialData = iComplaint_postRepository.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	public ResponseEntity<Complaint_post> add(@RequestBody Complaint_post complaint_post) {
		try {
			Complaint_post _Complaint_post = iComplaint_postRepository.save(complaint_post);
			return new ResponseEntity<>(_Complaint_post, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Complaint_post> updateTutorial(@PathVariable("id") int id,
			@RequestBody Complaint_post complaint_post) {
		
		Optional<Complaint_post> complaint_PostData = iComplaint_postRepository.findById(id);

		if (complaint_PostData.isPresent()) {
			Complaint_post _Complaint_Post = complaint_PostData.get();
			
			_Complaint_Post.setDate_comp(new Date());
			_Complaint_Post.setMsg(complaint_post.getMsg());
			_Complaint_Post.setStat(false);
			_Complaint_Post.setUser(complaint_post.getUser());
			

			return new ResponseEntity<>(iComplaint_postRepository.save(_Complaint_Post), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") int id) {
		try {
			iComplaint_postRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

