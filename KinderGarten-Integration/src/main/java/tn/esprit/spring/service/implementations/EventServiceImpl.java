package tn.esprit.spring.service.implementations;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Donnation;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Jackpot;
import tn.esprit.spring.entity.Participation;
import tn.esprit.spring.entity.ParticipationPK;
import tn.esprit.spring.entity.Type;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.IDonnationRepository;
import tn.esprit.spring.repository.IEventRepository;
import tn.esprit.spring.repository.IJackpotRepository;
import tn.esprit.spring.repository.IParticipantRepository;
import tn.esprit.spring.repository.IUserRepository;
import tn.esprit.spring.service.interfaces.IEventService;

@Service
public class EventServiceImpl implements IEventService {

	private static final long interval_milliSeconds = 60*60*1000;
	@Autowired
	private IEventRepository eventrepository;

	@Autowired
	private IJackpotRepository jackpotRepository;

	@Autowired
	private IParticipantRepository participantRepository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IDonnationRepository donnationRepository;

	/********************* ADMIN/PARENT/KINDERGARTEN-OWNER **************************/
	// 1-GET ALL EVENTS
	@Override
	public List<Event> getAllEvents() {
		List<Event> evts = new ArrayList<Event>();
		eventrepository.findAll().forEach(ev -> evts.add(ev));
		return evts;
	}

	// 2-GET EVENT BY ID
	@Override
	public Event getEventById(int id) {
		
		Event e = eventrepository.findById(id).get();
		if (e == null)
			return null;
		e.setViews(e.getViews() + 1);
	    int countView = eventrepository.updateViewsCountEvent(e.getViews() - 1, e.getId());
		countView++;

		return e;
	}

	/********************* ADMIN **************************/

	// 3-creating post mapping that post the event detail in the database
	@Override
	public void addEvent(Event event) {
		Jackpot j = new Jackpot();
		j.setSomme(0);
		event.setJackpot(j);
		eventrepository.save(event);
		jackpotRepository.save(j);
	}

	// 4-SAVE EVENT SIMPLE

	@Override
	public Event AddEvent(Event event) {
		Jackpot j = new Jackpot();
		j.setSomme(0);
		event.setJackpot(j);
		jackpotRepository.save(j);
		return eventrepository.save(event);
			}

	// 5-creating put mapping that updates the event detail
	@Override
	public int updateEvent(Event e, int id) {

		return eventrepository.updateEvent(e.getTitle(), e.getDate_begin(), e.getDate_end(),
				e.getDescription(), e.getPlace(),e.getPhoto(), e.getPrice(),e.getCollAmount(),e.getNbr_participants(), 
				e.getNbr_places(),e.isEarlyParticipants(),e.getDiscountPercentage(),e.getNbrTicketEarlyParticipants(),e.getType(),
				e.getId());
	}

	// 6- affecter event à un user
	@Override
	public String addParticipation(int iduser, int idevent) {
		Event event = eventrepository.findById(idevent).get();
		User u = userRepository.findById(iduser).get();
		Jackpot j = jackpotRepository.findJackpotEvent(event.getId());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		// int number = 0;
		Participation p = new Participation();
		ParticipationPK participationPK = new ParticipationPK();
		List<Participation> participations = (List<Participation>) participantRepository.findAll();
		int number = (int) ((event.getNbr_participants() + event.getNbr_places()) * 0.3f);
		// ken amal deja participation
		for (int i = 0; i < participations.size(); i++) {
			if (participations.get(i).getEvent().getId() == idevent
					&& participations.get(i).getUser().getId() == iduser)
				return "You are already participate !!";
		}
		if ((event.getNbr_places() > 0) && (event.getNbr_participants() < number)
				&& (event.isEarlyParticipants() == true)) {
			// calcul du pourcentage de la reduction
			float discPercent = (100f - event.getDiscountPercentage()) / 100f;
			float newPrice = event.getPrice() * discPercent;
			participationPK.setIdEvent(event.getId());
			participationPK.setIdUser(iduser);
			p.setParticipationDate(dateFormat.format(date));
			event.setNbr_places((event.getNbr_places()) - 1);
			event.setNbr_participants((event.getNbr_participants()) + 1);
			// System.out.println("coll amount =="+event.getCollAmount()+",
			// newPrice="+newPrice);
			event.setCollAmount(event.getCollAmount() + newPrice);
			u.setAccBalance(u.getAccBalance() - newPrice);
			p.setPrice(newPrice);
			p.setParticipationPK(participationPK);
			j.setSomme(j.getSomme() + newPrice);
			p.setParticipationDate(new Date().toString());
			// participationPK.setNumber(number);
			participantRepository.save(p);
			userRepository.save(u);
			jackpotRepository.save(j);
			return ("CONGRATULATIONS YOUR GET A DISCOUNT  :o !! THE NEW PRICE IS  " + newPrice + " INSTEAD OF"
					+ event.getPrice() + " nombre des participants avec reduction" + number);
		} else if (event.getNbr_places() == 0) {
			return "THERE IS NO AVAILABLE PLACES TO THIS EVENT :( !!";
		} else {
			if ((event.getNbr_places() > 0)) {
				participationPK.setIdEvent(event.getId());
				participationPK.setIdUser(iduser);
				p.setParticipationDate(dateFormat.format(date));
				event.setNbr_places(event.getNbr_places() - 1);
				event.setNbr_participants(event.getNbr_participants() + 1);
				// System.out.println("coll amount =="+event.getCollAmount()+",
				// newPrice="+event.getPrice());
				event.setCollAmount(event.getCollAmount() + event.getPrice());
				u.setAccBalance(u.getAccBalance() - event.getPrice());
				p.setPrice(event.getPrice());
				p.setParticipationPK(participationPK);
				// participationPK.setNumber(number);
				p.setParticipationDate(new Date().toString());
				j.setSomme(j.getSomme() + event.getPrice());
				participantRepository.save(p);
				userRepository.save(u);
				return ("AFFECTED SUCCUSSFULLY WITHOUT DISCOUNT :( " + event.getPrice()
						+ "PLZ TRY TO PARTICIPATE EARLY TO HAVE A DISCOUNT !!");

			}
		}
		return ("THIS EVENT IS OVER!!");
	}

	// 7-Returner event by id

	/********************* ADMIN/PARENT/KINDERGARTEN-OWNER **************************/

	// 8-retourner evenement par nom
	@Override
	public Event findEventByName(String name) {
		return eventrepository.findEventByName(name);
	}

	// 9-retourner evenement par type
	@Override
	public List<Event> filterEvent(Type type) {
		return eventrepository.filterByType(type);
	}

	// 10-retourner un Map des evenments les plus visités
	@Override
	public Map<Integer, Integer> getEventsByViews() {
		List<Integer> listId = new ArrayList<>();
		List<Integer> listViews = new ArrayList<>();
		Map<Integer, Integer> m = new HashMap<>();

		for (Event e : eventrepository.findAll()) {
			listId.add(e.getId());
			listViews.add(e.getViews());
		}
		List<Integer> sortedList = new ArrayList<>(listViews);
		Collections.sort(sortedList);

		for (int i = 0; i < 5; i++) {
			int max = sortedList.get(sortedList.size() - 1);
			int indice = listId.get(listViews.indexOf(max));
			m.put(indice, max);

			System.out.println(indice + " " + max);
			sortedList.remove(sortedList.size() - 1);
			listViews.set(listViews.indexOf(max), -1);
		}
		return m;

	}

	// 11-les evenements les plus visités
	@Override
	public List<String> displayBestEventsByViews() {

		List<String> list = new ArrayList<>();
		String s = "";
		List<Integer> listId = new ArrayList<>();
		List<Integer> listViews = new ArrayList<>();

		List<Event> listEvent = (List<Event>) eventrepository.findAll();

		for (Event e : listEvent) {
			listId.add(e.getId());
			listViews.add(e.getViews());

		}

		List<Integer> sortedList = new ArrayList<>(listViews);

		Collections.sort(sortedList);

		for (int i = 0; i < 10; i++) {
			int max = sortedList.get(sortedList.size() - 1);
			int ind = listId.get(listViews.indexOf(max));
			s = (i + 1) + "--Event: " + eventrepository.findById(ind).get().getTitle() + "  = with  " + max
					+ " views  ";
			list.add(s);
			sortedList.remove(sortedList.size() - 1);
			listViews.set(listViews.indexOf(max), -1);
		}
		return list;
	}

	/********************* ADMIN **************************/

	// 12-AFFECT TYPE TO EVENT
	@Override
	public String affecterTypeEvent(String type, int idevent) {
		Event event = eventrepository.findById(idevent).get();
		String msg = " ";
		try {
			for (Type t : Type.values()) {
				if (t == Type.valueOf(type)) {
					event.setType(Type.valueOf(type));
					eventrepository.save(event);
					return msg = "Type affected successfully !!";
				}
			}
		} catch (Exception e) {
			msg = "Failed to affect type!!";
		}
		return msg;
	}

	/********************* ADMIN/PARENT/KINDERGARTEN-OWNER **************************/

	// 13-UP COMING EVENTS
	@Override
	public List<Event> upcomeEvents() {
		List<Event> list = eventrepository.upcomingEvents();

		return list;
	}

	/********************* ADMIN/KINDERGARTEN-OWNER **************************/
	// 14-PASSED EVENTS
	@Override
	public List<Event> passedEvents() {
		List<Event> e = eventrepository.passedEvents();
		return e;
	}

	// 15-DELETE EVENT && REFUND CONSUMER
	@Override
	public void refundUsers(int eid) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		Event ev = eventrepository.findById(eid).get();
		User u = new User();
		List<Participation> participationsOfEvent = participantRepository.Particiapations(ev);
		System.out.println("hiii==");
		// Notification n = new Notification();
		if (!ev.getParticipations().isEmpty()) {
			for (Participation p : participationsOfEvent) {
				// int u = new User();//user id
				u = userRepository.findById(p.getUser().getId()).get();
				float refundedAmount = p.getPrice();
				// remboursement du monatant du don
				ev.setCollAmount(ev.getCollAmount() - refundedAmount);
				u.setAccBalance(u.getAccBalance() + refundedAmount);
				userRepository.save(u);
				System.out.println("hiiiiii==");
				participantRepository.delete(p);
				eventrepository.save(ev);
			}

		} else {
			System.out.println("event without participations");
		}
		List<Donnation> donationsResult = donnationRepository.DonationOfEvent(ev);
		for (Donnation d : donationsResult) {
			User user = d.getUser();
			float refundedAmount = d.getAmount();// flous ta3 donation
			System.out.println("donation=" + refundedAmount);
			System.out.println("jackpot before==" + ev.getJackpot());
			ev.getJackpot().setSomme(ev.getJackpot().getSomme() - refundedAmount);
			System.out.println("jackpot after retrieve money==" + ev.getJackpot().getSomme());
			System.out.println("collAmount before==" + ev.getCollAmount());
			ev.setCollAmount(ev.getCollAmount() - refundedAmount);
			System.out.println("jackpot==" + ev.getCollAmount());
			System.out.println("Accurance balancebefore=" + u.getAccBalance());
			u.setAccBalance(u.getAccBalance() + refundedAmount);
			System.out.println("accuranceBalance=" + u.getAccBalance());
			donnationRepository.deleteById(d.getId());
			eventrepository.save(ev);
			userRepository.save(u);
		}
	}

	// DELETE EVENT
	@Override
	public void deleteEvent(int id) {
		Event e = eventrepository.findById(id).get();
		eventrepository.deleteById(id);

	}

	/********************* ADMIN/PARENT/KINDERGARTEN-OWNER **************************/

	// 16-DISPLAY EVENTS BY PARTICIPARTIONS
	@Override
	public List<String> displayEventsByParticipants() {
		// TODO Auto-generated method stub
		List<Event> events = (List<Event>) eventrepository.findAll();
		List<Integer> listIdEvent = new ArrayList<>();
		List<Integer> listNbrParticipantEvent = new ArrayList<>();
		List<String> results = new ArrayList<>();
		String message = "";
		for (Event event : events) {
			listIdEvent.add(event.getId());
			listNbrParticipantEvent.add(event.getNbr_participants());
		}

		List<Integer> sortListed = new ArrayList<>(listNbrParticipantEvent);
		Collections.sort(sortListed);
		for (int i = 0; i < 10; i++) {
			int max = sortListed.get(sortListed.size() - 1);
			int idEventByPart = listIdEvent.get(listNbrParticipantEvent.indexOf(max));
			message = "Event " + eventrepository.findById(idEventByPart).get().getTitle() + "     with " + max
					+ " Participations  :)";
			results.add(message);
			sortListed.remove(sortListed.size() - 1);
			listNbrParticipantEvent.set(listNbrParticipantEvent.indexOf(max), -1);
		}

		return results;
	}

	// 17-DISPLAY EVENTS BY COLLABORATION AMOUNT
	@Override
	public List<String> displayEventsByCollAmount() {
		List<Event> events = (List<Event>) eventrepository.findAll();
		List<Integer> listIdEvent = new ArrayList<>();
		List<Float> listCollAmountEvent = new ArrayList<>();
		List<String> results = new ArrayList<>();
		String message = "";
		for (Event event : events) {
			listIdEvent.add(event.getId());
			listCollAmountEvent.add(event.getCollAmount());
		}
		List<Float> sortListed = new ArrayList<>(listCollAmountEvent);
		Collections.sort(sortListed);
		for (int i = 0; i < 10; i++) {
			float max = sortListed.get(sortListed.size() - 1);// derniere valeur
			int idEventByPart = listIdEvent.get(listCollAmountEvent.indexOf(max));
			message = "Event " + eventrepository.findById(idEventByPart).get().getTitle() + "    with    " + max
					+ "   dinars  ,  Collectd Amount  ";
			results.add(message);
			sortListed.remove(sortListed.size() - 1);
			listCollAmountEvent.set(listCollAmountEvent.indexOf(max), (float) -1);
		}
		return results;
	}

	// 18-DISPLAY ALL PARTICIPATIONS

	@Override
	public void findEventById(int id) {
		eventrepository.findById(id).get();
	}

	@Override
	public Event findbyId(int id) {
		return eventrepository.findById(id).get();
	}
	
	public String ResultEvent(List<Event> events,int i) {
		return "Event "+i+""+"Titre : "+events.get(i).getTitle()+
				""+"--Description : "+events.get(i).getDescription()+
				""+"--Place : "+events.get(i).getPlace()+
				""+"--Price : "+events.get(i).getPrice()+
				""+"--Collested Amount : "+events.get(i).getCollAmount()+
				""+"--Number Places : "+events.get(i).getNbr_places()+
				""+"--Number Participants : "+events.get(i).getNbr_participants()+
				""+"--Type : "+events.get(i).getType();
	}
	
	@Override
	public List<String> getEventTwoDatesBeetween(Date date1, Date date2) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		List<Event>events = (List<Event>) eventrepository.findAll();
		List<String>results = new ArrayList<>();
		
		if(date1.after(date2)) {
			return null;
		}
		for(int i = 0 ; i<events.size();i++) {
			if((events.get(i).getDate_begin().after(date1) &&( events.get(i).getDate_begin().before(date2)))) {
				results.add(ResultEvent(events,i));
			}
			
		}
		if(results.isEmpty()) {
			 results.add("Event Not Found between two dates we are sorry :( :( ");
			 return results;
		}
		return results;
	}
	@Scheduled(fixedRate=interval_milliSeconds)
	@Override
	public void reintializeJackPotAfterDateEvent(int idevent) {
		
		Jackpot jack = jackpotRepository.findJackpotEvent(idevent);
		
		Event event = eventrepository.findById(idevent).get();
		Date now = new Date();
		if(now.getTime() - event.getDate_begin().getTime() >=1) {
			jack.setSomme(0);
			jackpotRepository.save(jack);
		}
	}
}