package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Donnation;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.service.interfaces.IDonnationService;
import tn.esprit.spring.service.interfaces.IEventService;
@Secured({ "ROLE_PARENT", "ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
@RestController
@RequestMapping(path = "/event/donnation/")
public class DonnationController {

	@Autowired
	IDonnationService donnationService;

	@Autowired
	IEventService eventservice;

	// 1-creating put mapping that updates the event detail
	@PostMapping("/donation-event/{eventid}/{uid}/{amount}")
	public  ResponseEntity<String> DonnationOfEvent(@PathVariable("eventid") int eventid, @PathVariable("uid") int uid,
			@PathVariable("amount") float amount) {
		String result = donnationService.Donnation(eventid, uid, amount);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// 2-retourner la listes des donnation d'un event
	@GetMapping("/displayDonationsEvent/{idevent}")
	public List<Donnation> getDonationEvention(@PathVariable("idevent") int idevent) {
		return donnationService.getDonationEvention(eventservice.findbyId(idevent));
	}

}
