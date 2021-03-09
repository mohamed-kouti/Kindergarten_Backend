package tn.esprit.spring.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.service.interfaces.IEventService;

@RestController
@RequestMapping(path = "/event")
public class EvenementController {
 
	@Autowired
	IEventService eventService;
	
	@GetMapping(value="/retrieves-all-event")
	@ResponseBody
	public List<Event> getEvent()
	{
		List<Event> list= eventService.retreiveAllEvent();
		return list;
	}
	
	@PostMapping(value="/save-event")
	public Event saveEvent(@RequestBody Event ev){
		eventService.AddEvent(ev);
		return ev;
	}
	
	@PutMapping("/update-event")
	@ResponseBody
	public ResponseEntity<String> UpdateEvent(@RequestBody Event ev){
		eventService.UpdateEvent(ev);
		return new ResponseEntity<String>("Event Updated Successfully",HttpStatus.OK);
	}
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<String> DeleteEvent(@PathVariable int id){
		eventService.DeleteEvent(id);
		return new ResponseEntity<String>("Event Deleted succssefully",HttpStatus.OK );
	}
}
