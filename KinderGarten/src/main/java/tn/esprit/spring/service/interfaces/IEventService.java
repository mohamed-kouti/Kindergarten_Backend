package tn.esprit.spring.service.interfaces;



import java.util.List;
import java.util.Map;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Type;
public interface IEventService {
	
	public void AddEvent(Event event);
	public void UpdateEvent(Event event);
	public void DeleteEvent(int id);
	public List<Event> retreiveAllEvent();
	public Event getEventById(int id);
	public Event findEventByName(String name);
	public List<Event> filterEvent(Type type);
	public Event Update2Event(Event e, int id);
	/*public String affecterEventUser(int iduser,int idevent);*/
	
    public Map<Integer,Integer>getEventsByViews();
	public List<String> MostVisitedEvents() ;
}
