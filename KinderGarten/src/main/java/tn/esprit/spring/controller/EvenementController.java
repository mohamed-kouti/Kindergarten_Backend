package tn.esprit.spring.controller;

import java.util.List;
import java.util.Map;

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
import tn.esprit.spring.entity.Type;
import tn.esprit.spring.service.implementations.EventServiceImpl;

@RestController
@RequestMapping(path = "/event")
public class EvenementController {
 
	@Autowired
	EventServiceImpl eventServiceImpl;
	
	//retourner tous les events
	@GetMapping(value="/retrieves-all-event")
	@ResponseBody
	public List<Event> getEvent()
	{
		List<Event> list= eventServiceImpl.retreiveAllEvent();
		return list;
	}
	
	//retourner evenement par id
	@GetMapping(value="/get-event/{id}")
	@ResponseBody
	public Event getEvent(@PathVariable("id") int id)
	{
		return eventServiceImpl.getEventById(id);
	}
	
	//ajouter un événement
	@PostMapping(value="/save-event")
	public Event saveEvent(@RequestBody Event ev){
		eventServiceImpl.AddEvent(ev);
		return ev;
	}
	
	//mettre à jour un evenement
	@PutMapping("/update-event")
	@ResponseBody
	public ResponseEntity<String> UpdateEvent(@RequestBody Event ev){
		eventServiceImpl.UpdateEvent(ev);
		return new ResponseEntity<String>("Event Updated Successfully",HttpStatus.OK);
	}
	
	//supprimer un event par id
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<String> DeleteEvent(@PathVariable int id){
		eventServiceImpl.DeleteEvent(id);
		return new ResponseEntity<String>("Event Deleted succssefully",HttpStatus.OK );
	}
	
	//afficher event par nom
	@GetMapping(value="/Display-Name/{name}")
	public Event findEventByName(@PathVariable String name){
		Event e=eventServiceImpl.findEventByName(name);
		return e;
	}
	
	//afficher event par type
	@GetMapping(value="/Display-Type/{type}")
	public List<Event> filterEvent(@PathVariable Type type){
		List<Event> e= eventServiceImpl.filterEvent(type);
		return e; 
	}
	
	//retourner les evenet 
	@GetMapping("/event/Events-By-Views")
	public Map<Integer, Integer> bestEventsByViews(){
		return eventServiceImpl.getEventsByViews();
		}
	
	//retourner les evnets les plus visités
	@GetMapping("/event/MostVisitedEvents")
	public List<String> MostVisitedEvents(){
		return eventServiceImpl.MostVisitedEvents();
		}
	
	
	
	
	
}
