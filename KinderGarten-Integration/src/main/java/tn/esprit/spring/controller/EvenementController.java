package tn.esprit.spring.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import tn.esprit.spring.config.FileUploadUtil;
import tn.esprit.spring.entity.Event;

import tn.esprit.spring.entity.Participation;
import tn.esprit.spring.entity.Type;
import tn.esprit.spring.service.implementations.EventServiceImpl;
import tn.esprit.spring.service.implementations.ParticipationServiceImpl;
//@Secured({ "ROLE_PARENT", "ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
@RestController
@RequestMapping(path = "/event")
public class EvenementController {

	@Autowired
	private EventServiceImpl eventServiceImpl;

	@Autowired
	private ParticipationServiceImpl participationServiceImpl;

	/********************* ADMIN   /  KINDERGARTEN-OWNER **************************/
	
	// 1-GET ALL EVENTS
	@Secured({"ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
	@GetMapping("/get-all-events")
	public List<Event> getAllEvents() {
		return eventServiceImpl.getAllEvents();
	}

	// 2-GET EVENT BY ID
	@Secured({"ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
	@GetMapping(value = "/detail-event/{eventid}")
	@ResponseBody
	public Event getEvent(@PathVariable("eventid") int eventid) {
		return eventServiceImpl.getEventById(eventid);
	}

	/********************* ADMIN **************************/

	// creating post mapping that post the event detail in the database
	/*ObjectMapper objectMapper = new ObjectMapper();

	// 3-creating post mapping that post the event detail in the database
	@PostMapping(value = "/add-event", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public Event addEvent(@RequestPart("evJson") String evJson, @RequestPart("photo") MultipartFile file)
			throws IOException {
		Event ev = new Event();
		String fileNameRepo = StringUtils.cleanPath(file.getOriginalFilename());
		String uploadDir = "uploads/";
		System.out.println("photo name =" + fileNameRepo);
		try {
			ev = objectMapper.readValue(evJson, Event.class);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/")
					.path(fileNameRepo).toUriString();
			System.out.println("file url =====>" + fileDownloadUri);
			ev.setPhoto(fileDownloadUri.getBytes());
			FileUploadUtil.saveFile(uploadDir, fileNameRepo, file);

		} catch (IOException e) {
			e.printStackTrace();
		}
		eventServiceImpl.addEvent(ev);
		return ev;
	}*/
	// 4-SAVE EVENT SIMPLE
	@Secured({"ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
	@PostMapping(value = "/save-event")
	public Event saveEvent(@RequestBody Event ev) {
		eventServiceImpl.AddEvent(ev);
		return ev;
	}

	// 5-creating put mapping that updates the event detail 
	@Secured({"ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
	@PutMapping("/update-event/{id}")
	public ResponseEntity<String> updateEvent(@RequestBody Event events, @PathVariable("id") int id) {

		eventServiceImpl.updateEvent(events, id);
		return new ResponseEntity<String>("Event updated successfully", HttpStatus.OK);
	}

	// 6- affecter event à un user
	@Secured({"ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
	@PutMapping("/affecter-event/{iduser}/{idevent}")
	public String participateToEvent(@PathVariable("iduser") int iduser, @PathVariable("idevent") int idevent) {
		String result = eventServiceImpl.addParticipation(iduser, idevent);
		return result;
	}

	// 7-Returner event by id
	@Secured({"ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
	@GetMapping("/retrieve-Event-ById/{id}")
	public Event getEventById(@PathVariable("id") int id) {
		Event ev = eventServiceImpl.getEventById(id);
		return ev;
	}

	/********************* ADMIN/PARENT/KINDERGARTEN-OWNER **************************/

	// 8-retourner evenement par nom
	@Secured({"ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
	@GetMapping("/retrieve-Event-ByName/{name}")
	public Event getEventByName(@PathVariable String name) {
		Event ev = eventServiceImpl.findEventByName(name);

		return ev;
	}

	// 9-retourner evenement par type
	@Secured({ "ROLE_PARENT", "ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
	@GetMapping("/retrieve-Event-ByType/{type}")
	public List<Event> getEventByType(@PathVariable Type type) {
		List<Event> ev = eventServiceImpl.filterEvent(type);
		return ev;
	}

	// 10-retourner un Map des evenments les plus visités
	@Secured({"ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
	@GetMapping("/bestEventsByViews")
	public Map<Integer, Integer> bestEventsByViews() {
		return eventServiceImpl.getEventsByViews();
	}

	// 11-les evenements les plus visités
	@GetMapping("/displaybestEventsByViews")
	public List<String> displaybestEventsByViews() {
		return eventServiceImpl.displayBestEventsByViews();
	}

	/********************* ADMIN **************************/

	// 12-AFFECT TYPE TO EVENT
	@PutMapping("/affecter-type-event/{type}/{idevent}")
	public String affecterCategoryEvent(@PathVariable("type") String type, @PathVariable("idevent") int idevent) {

		return eventServiceImpl.affecterTypeEvent(type, idevent);

	}

	/********************* ADMIN/PARENT/KINDERGARTEN-OWNER **************************/

	// 13-UP COMING EVENTS
	@Secured({"ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
	@GetMapping("/upcomingEvent")
	public List<Event> upcomingEvents() {
		List<Event> upevents = eventServiceImpl.upcomeEvents();
		// System.out.println("hi=" + upevents);
		return upevents;
	}

	/********************* ADMIN **************************/
	// 14-PASSED EVENTS
	@Secured({"ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
	@GetMapping(value = "/passedEvents")
	public List<Event> passedEvents() {
		List<Event> e = eventServiceImpl.passedEvents();
		return e;
	}

	// 15-DELETE EVENT && REFUND CONSUMER
	@Secured({"ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
	@DeleteMapping("/delete-event/{event-id}")
	@ResponseBody
	public ResponseEntity<String> deleteEvent(@PathVariable("event-id") int eventID) {

		eventServiceImpl.refundUsers(eventID);

		eventServiceImpl.deleteEvent(eventID);

		return new ResponseEntity<String>("Event canceled with upgrading price and refund money to user participate",
				HttpStatus.ACCEPTED);
	}

	/********************* ADMIN/PARENT/KINDERGARTEN-OWNER **************************/

	// 16-DISPLAY EVENTS BY PARTICIPARTIONS
	@GetMapping("/displayBestEventsByParticipations")
	public List<String> displayBestEventsByParticipations() {
		return eventServiceImpl.displayEventsByParticipants();
	}

	// 17-DISPLAY EVENTS BY COLLABORATION AMOUNT
	@GetMapping("/displayEventsByCollAmount")
	public List<String> displayEventsByCollAmount() {
		return eventServiceImpl.displayEventsByCollAmount();

	}

	// 18-DISPLAY ALL PARTICIPATIONS
	@GetMapping("/retrieve-all-Participations")
	public List<Participation> getParticipations() {
		return participationServiceImpl.participationsList();
	}
	
	//19-Event betweeen 2 dates
		@Secured({ "ROLE_PARENT", "ROLE_ADMIN","ROLE_KINDERGARTEN_OWNER" })
		@GetMapping("/event/getEventsBetweenTwoDates/{date1}/{date2}")
		public List<String> getEventBetweenTwoDates(@PathVariable("date1")String date1,@PathVariable("date2")String date2) throws java.text.ParseException {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			Date date1Converted = dateFormat.parse(date1);
			Date date2Converted = dateFormat.parse(date2);
			
	return eventServiceImpl.getEventTwoDatesBeetween(date1Converted,date2Converted);
		}
}