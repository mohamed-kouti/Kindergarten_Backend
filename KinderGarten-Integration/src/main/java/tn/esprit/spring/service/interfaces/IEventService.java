package tn.esprit.spring.service.interfaces;



import java.util.Date;
import java.util.List;
import java.util.Map;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Type;

public interface IEventService {

	// 1-GET ALL EVENTS
	public List<Event> getAllEvents();
	// 2-GET EVENT BY ID
	public Event getEventById(int id);
	// 3-creating post mapping that post the event detail 
	public void addEvent(Event e);
	// 4-SAVE EVENT SIMPLE
	public Event AddEvent(Event event);
	// 5-creating put mapping that updates the event detail 
	public int updateEvent(Event e , int id);
	// 6- affecter event à un user
	public String addParticipation(int iduser,int idevent);
	// 7-Returner event by id
	
	// 8-retourner evenement par nom
	public Event findEventByName(String name);
	// 9-retourner evenement par type
	public List<Event> filterEvent(Type type);
	// 10-retourner un Map des evenments les plus visités
	public Map<Integer,Integer>getEventsByViews();
	// 11-les evenements les plus visités
	public List<String> displayBestEventsByViews() ;
	// 12-AFFECT TYPE TO EVENT
	public String affecterTypeEvent(String type,int idevent);
	// 13-UP COMING EVENTS
	public List<Event> upcomeEvents();
	// 14-PASSED EVENTS
	public List<Event> passedEvents();
	// 15-DELETE EVENT && REFUND CONSUMER
	public void deleteEvent(int id);
	public void refundUsers(int eid) ;
	// 16-DISPLAY EVENTS BY PARTICIPARTIONS
	public List<String> displayEventsByParticipants() ;
	// 17-DISPLAY EVENTS BY COLLABORATION AMOUNT
	public List<String> displayEventsByCollAmount() ;
	public void findEventById(int id) ;
	public Event findbyId(int id);
	List<String> getEventTwoDatesBeetween(Date date1, Date date2);
	//20-initialize jackpot
	public 	void reintializeJackPotAfterDateEvent(int idevent);
	//public Void GetJackpotByEvnt (int idevent);

}
