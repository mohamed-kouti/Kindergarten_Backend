package tn.esprit.spring.service.interfaces;



import java.util.Date;
import java.util.List;
import java.util.Map;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Type;

public interface IEventService {

	public void addEvent(Event e);
	public void deleteEvent(int id);
	public int updateEvent(Event e , int id);
	public List<Event> getAllEvents();
	public Event getEventById(int id);
	public Event findEventByName(String name);
	public List<Event> filterEvent(Type category);
	//public String addParticipation(int iduser,int idevent);
	public Map<Integer,Integer>getEventsByViews();
	public List<String> displayBestEventsByViews() ;
	public String affecterTypeEvent(String type,int idevent);
	public List<Event> upcomeEvents();
	public void refundUsers(int eid) ;
	public void findEventById(int id) ;
	public List<String> displayEventsByParticipants() ;
	public List<String> displayEventsByCollAmount() ;
	public Event findbyId(int id);
	public List<String> getEventTwoDatesBeetween(Date date1, Date date2) ;
	
	
	/*// 1-ajouter event
	public void AddEvent(Event event);
	
	// 2-supprimer event
	public void DeleteEvent(int id);
	
	// 3-retourner par id
	public Event findEventById(int id);
	
	// 4-update wahda okhra simple barcha
	public void UpdateEvent(Event event);
	
	//5-update2Event
	public Event Update2Event(Event e, int id);
	
	//6-retreive all user
	public List<Event> retreiveAllEvent();
	
	//7-getEventById
	public Event getEventById(int id);
	
	//8-retourner par nom
	public Event findEventByName(String name);
	
	//9-retourner event par type filter event
	public List<Event> filterEvent(Type type);
	
	// 10- les 5 les plus visitées par rapport au nombre des vues
	public Map<Integer,Integer>getEventsByViews();
	
	//11-evenement qui vont venir
	public List<Event> upcomingEvents();
	
	//12-les evenements qui ont depassé la date systeme
	public List<Event> passedEvents();
	
	// 13-Ajouter particiaption à un event et user
	public String addParticipation(int iduser,int idevent);
	
	//14-events plus visitées
	public List<String> MostVisitedEvents();
	
	//15-affecter type à un event 	
	public String affecterTypeEvent(String type,int idevent);
	
	//16-remboursser en cas d'annulation 
	public void refundUsers(int eid) ;
	*/
}
