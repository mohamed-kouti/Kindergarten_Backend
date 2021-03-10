package tn.esprit.spring.service.implementations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Jackpot;
import tn.esprit.spring.entity.Type;
import tn.esprit.spring.repository.IEventRepository;
import tn.esprit.spring.repository.IJackpotRepository;
import tn.esprit.spring.service.interfaces.IEventService;
@Service
public class EventServiceImpl implements IEventService {

	@Autowired
	IEventRepository eventrepository;
	@Autowired
	IJackpotRepository jackpotRepository;
	
	//ajouter event et jackpot
	@Override
	public void AddEvent(Event event) {
		Jackpot j=new Jackpot();
		j.setSomme(0);
		event.setJackpot(j);
		eventrepository.save(event);
	}

	//supprimer event
	@Override
	public void DeleteEvent(int id) {
		eventrepository.deleteById(id);
		
	}
	
	//
	@Override
	public Event Update2Event(Event e, int id){
		Event ev=eventrepository.findById(id).get();
		try{
			ev.setTitle(e.getTitle());
			ev.setDescription(e.getDescription());
			ev.setDate_begin(e.getDate_begin());
		}catch(NullPointerException nullPointerException){
			System.out.println("THIS EVENT IS MAYBE OVER OR NONEXISTANT");
		}
		return eventrepository.save(ev);
		}
	
	//update wahda okhra simple barcha 
	@Override
	public void UpdateEvent(Event event) {
		 eventrepository.save(event);
	}
	


	@Override
	public List<Event> retreiveAllEvent() {
		List<Event> evts= new ArrayList<Event>();
		eventrepository.findAll().forEach(ev->evts.add(ev));
		//hawka wahda b expression lambda w theniya 
		/*for (Event ev:evts){
			l.info("Event :"+ ev);
		}*/
		return evts;
	}
	
	//retourner par id
	@Override
	public Event getEventById(int id){
	return eventrepository.findById(id).get();
	}
	
	//retourner par nom
	@Override
	public Event findEventByName(String name){
		return eventrepository.findEventByName(name);
	}
	
	//retourner event par type
	@Override
	public List<Event> filterEvent(Type type){
		return eventrepository.filterByType(type);
	}
	
	//les 5 les plus visitées par rapport au nombre des vues
	@Override
	 public Map<Integer,Integer> getEventsByViews(){
		List<Integer> id=new ArrayList<>();
		List<Integer> vu= new ArrayList<>();
		Map<Integer,Integer> m = new HashMap<>();
		
		for (Event e:eventrepository.findAll()){
			id.add(e.getId());
			vu.add(e.getId());
		}
		List<Integer> sortedList =new ArrayList<>(vu);
		Collections.sort(sortedList);
		
	for (int i=0; i<5;i++){
		int max= sortedList.get(sortedList.size()-1);
		int indice=id.get(vu.indexOf(max));
		m.put(indice, max);
		System.out.println(indice +" "+ max);
		sortedList.remove(sortedList.size()-1);
		vu.set(vu.indexOf(max), -1);
	}
	return m;
		
	}
	
	//afficher les 5 evenement les plus visités
	@Override
	public List<String> MostVisitedEvents() {
		
		List<String>l = new ArrayList<>();
		String s ="";
		List<Integer>lId = new ArrayList<>();
		List<Integer>lV = new ArrayList<>();
		List<Event> lE = (List<Event>)eventrepository.findAll();
		for(Event e : lE) {
			lId.add(e.getId());
			lV.add(e.getViews());
	}
List<Integer> sortedList = new ArrayList<>(lV);
		
		Collections.sort(sortedList);
		
		for(int i = 0 ; i<3 ; i++) {
			int max = sortedList.get(sortedList.size()-1);
			int ind = lId.get(lV.indexOf(max));
			s = (i+1)+"-Event: "+eventrepository.findById(ind).get().getTitle()+" with "+max+" views ";
			l.add(s);
			sortedList.remove(sortedList.size()-1);
			lV.set(lV.indexOf(max), -1);
			}
		return l;
	}
	
}
 