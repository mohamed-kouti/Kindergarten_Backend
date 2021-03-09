package tn.esprit.spring.service.implementations;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.esprit.spring.entity.Event;
import tn.esprit.spring.repository.IEventRepository;
import tn.esprit.spring.service.interfaces.IEventService;
@Service
public class EventServiceImpl implements IEventService {

	private static final Logger l = LogManager.getLogger(EventServiceImpl.class);
	@Autowired
	IEventRepository eventrepository;
	
	@Override
	public void AddEvent(Event event) {
		 eventrepository.save(event);
	}

	@Override
	public void UpdateEvent(Event event) {
		 eventrepository.save(event);
	}

	@Override
	public void DeleteEvent(int id) {
		eventrepository.deleteById(id);
		
	}

	@Override
	public List<Event> retreiveAllEvent() {
		List<Event> evts= (List<Event>) eventrepository.findAll();
		for (Event ev:evts){
			l.info("Event :"+ ev);
		}
		return evts;
	}
	
	
}
 