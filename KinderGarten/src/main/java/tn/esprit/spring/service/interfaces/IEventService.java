package tn.esprit.spring.service.interfaces;



import java.util.List;

import tn.esprit.spring.entity.Event;



public interface IEventService {
	
	public void AddEvent(Event event);
	public void UpdateEvent(Event event);
	public void DeleteEvent(int id);
	public List<Event> retreiveAllEvent();


}
