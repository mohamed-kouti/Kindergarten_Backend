package tn.esprit.spring.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.el.parser.ParseException;

import tn.esprit.spring.config.FileUploadUtil;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Jackpot;
import tn.esprit.spring.entity.Participation;
import tn.esprit.spring.entity.Type;
import tn.esprit.spring.service.implementations.EventServiceImpl;
import tn.esprit.spring.service.implementations.JackpotServiceImpl;
import tn.esprit.spring.service.implementations.ParticipationServiceImpl;

@RestController
@RequestMapping(path = "/event")
public class EvenementController {

	@Autowired
	private EventServiceImpl eventServiceImpl;

	@Autowired
	private ParticipationServiceImpl participationServiceImpl;

	/********************* ADMIN/PARENT/KINDERGARTEN-OWNER **************************/

	// 1-GET ALL EVENTS
	@GetMapping("/get-all-events")
	public List<Event> getAllEvents() {
		return eventServiceImpl.getAllEvents();
	}

	// 2-GET EVENT BY ID
	@GetMapping(value = "/detail-event/{eventid}")
	@ResponseBody
	public Event getEvent(@PathVariable("eventid") int eventid) {
		return eventServiceImpl.getEventById(eventid);
	}

	/********************* ADMIN **************************/

	// creating post mapping that post the event detail in the database
	ObjectMapper objectMapper = new ObjectMapper();

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
	}

	// 4-creating put mapping that updates the event detail
	@PutMapping("/update-event/{id}")
	public ResponseEntity<String> updateEvent(@RequestBody Event events, @PathVariable("id") int id) {

		eventServiceImpl.updateEvent(events, id);
		return new ResponseEntity<String>("Event updated successfully", HttpStatus.OK);
	}

	// 5- affecter event à un user
	@PutMapping("/affecter-event/{iduser}/{idevent}")
	public String participateToEvent(@PathVariable("iduser") int iduser, @PathVariable("idevent") int idevent) {
		String result = participationServiceImpl.addParticipation(iduser, idevent);
		return result;
	}

	// 6-Returner event by id
	@GetMapping("/retrieve-Event-ById/{id}")
	public Event getEventById(@PathVariable("id") int id) {
		Event ev = eventServiceImpl.getEventById(id);
		return ev;
	}

	/********************* ADMIN/PARENT/KINDERGARTEN-OWNER **************************/

	// 7-retourner evenement par nom
	@GetMapping("/retrieve-Event-ByName/{name}")
	public Event getEventByName(@PathVariable String name) {
		Event ev = eventServiceImpl.findEventByName(name);

		return ev;
	}

	// 8-retourner evenement par type
	@GetMapping("/retrieve-Event-ByType/{type}")
	public List<Event> getEventByType(@PathVariable Type type) {
		List<Event> ev = eventServiceImpl.filterEvent(type);
		return ev;
	}

	// 9-retourner un Map des evenments les plus visités
	@GetMapping("/bestEventsByViews")
	public Map<Integer, Integer> bestEventsByViews() {
		return eventServiceImpl.getEventsByViews();
	}

	// 10-les evenements les plus visités
	@GetMapping("/displaybestEventsByViews")
	public List<String> displaybestEventsByViews() {
		return eventServiceImpl.displayBestEventsByViews();
	}

	/********************* ADMIN **************************/

	// 11-AFFECT TYPE TO EVENT
	@PutMapping("/affecter-type-event/{type}/{idevent}")
	public String affecterCategoryEvent(@PathVariable("type") String type, @PathVariable("idevent") int idevent) {

		return eventServiceImpl.affecterTypeEvent(type, idevent);

	}

	/********************* ADMIN/PARENT/KINDERGARTEN-OWNER **************************/

	// 12-UP COMING EVENTS
	@GetMapping("/upcomingEvent")
	public List<Event> upcomingEvents() {
		List<Event> upevents = eventServiceImpl.upcomeEvents();
		System.out.println("hi=" + upevents);
		return upevents;
	}

	/********************* ADMIN **************************/
	// 13-DELETE EVENT && REFUND CONSUMER
	@DeleteMapping("/delete-event/{event-id}")
	@ResponseBody
	public ResponseEntity<String> deleteEvent(@PathVariable("event-id") int eventID) {

		eventServiceImpl.refundUsers(eventID);// refund contributions participations prices to its users

		eventServiceImpl.deleteEvent(eventID);

		return new ResponseEntity<String>("Event canceled with upgrading price and refund money to user participate",
				HttpStatus.ACCEPTED);
	}

	/********************* ADMIN/PARENT/KINDERGARTEN-OWNER **************************/

	// 14-DISPLAY EVENTS BY PARTICIPARTIONS
	@GetMapping("/displayBestEventsByParticipations")
	public List<String> displayBestEventsByParticipations() {
		return eventServiceImpl.displayEventsByParticipants();
	}

	// 15-DISPLAY EVENTS BY COLLABORATION AMOUNT
	@GetMapping("/displayEventsByCollAmount")
	public List<String> displayEventsByCollAmount() {
		return eventServiceImpl.displayEventsByCollAmount();

	}

	// 16-DISPLAY ALL PARTICIPATIONS
	@GetMapping("/retrieve-all-Participations")
	public List<Participation> getParticipations() {
		return participationServiceImpl.participationsList();
	}

	// 17-DISPLAY EVENTS BTW 2 DATES
	@GetMapping("/getEventsBetweenTwoDates/{date1}/{date2}")
	public List<String> getEventBetweenTwoDates(@PathVariable("date1") String date1,@PathVariable("date2") String date2) throws java.text.ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Date date1Converted = dateFormat.parse(date1);
		Date date2Converted = dateFormat.parse(date2);
		return eventServiceImpl.getEventTwoDatesBeetween(date1Converted, date2Converted);
	}

	/*
	 * des services momken nzidhom men ba3d oumouri ma3netha // 6-retreive all
	 * events
	 * 
	 * @GetMapping(value = "/retrieves-all-event")
	 * 
	 * @ResponseBody public List<Event> retreiveAllEvent() { List<Event> list =
	 * eventServiceImpl.retreiveAllEvent(); return list; } // 4-update wahda
	 * okhra simple barcha
	 * 
	 * @PutMapping("/update-event")
	 * 
	 * @ResponseBody public ResponseEntity<String> UpdateEvent(@RequestBody
	 * Event ev) { eventServiceImpl.UpdateEvent(ev); return new
	 * ResponseEntity<String>("Event Updated Successfully simple barcha",
	 * HttpStatus.OK); } // 1-ajouter event simple
	 * 
	 * @PostMapping(value = "/save-event") public Event saveEvent(@RequestBody
	 * Event ev) { eventServiceImpl.AddEvent(ev); return ev; } // 12-les
	 * evenements qui ont depassé la date systeme
	 * 
	 * @GetMapping(value = "/passedEvents") public List<Event> passedEvents() {
	 * List<Event> e = eventServiceImpl.passedEvents(); return e; } // affecter
	 * evenement a un JE
	 * 
	 * @PutMapping(value="/affecter-event/{iduser}/{idevent}") public String
	 * affecterEventUser(@PathVariable ("iduser") int
	 * iduser,@PathVariable("idevent") int idevent) { return
	 * eventServiceImpl.affecterEventUser(iduser,idevent); } // retourner le
	 * jackpot liée à un event /*
	 * 
	 * @GetMapping("/findJackpot") public List<Jackpot> findJackpot(Event event)
	 * { List<Jackpot> jackpot = (List<Jackpot>)
	 * jackpotServiceImpl.findJackpot(event); return jackpot; }
	 */
}