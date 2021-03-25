package tn.esprit.spring.service.implementations;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	/*********************ADMIN/PARENT/KINDERGARTEN-OWNER**************************/
	//1-retreive all user
		@Override
		public List<Event> getAllEvents() {
			List<Event> evts = new ArrayList<Event>();
			eventrepository.findAll().forEach(ev -> evts.add(ev));
			return evts;
		}
	
		//7-getEventById
		@Override
		public Event getEventById(int id) {
			int countView;
			Event e = eventrepository.findById(id).get();
			if(e == null) return null;
			e.setViews(e.getViews()+1);
		    countView = eventrepository.updateViewsCountEvent(e.getViews()-1,e.getId());
		    countView++;
				
			return e ; 
			}
		
	/********************* ADMIN **************************/
	// 1-ajouter event et jackpot
	@Override
	public void addEvent(Event event) {
		Jackpot j = new Jackpot();
		j.setSomme(0);
		event.setJackpot(j);
		eventrepository.save(event);
		jackpotRepository.save(j);
	}

	// 2-supprimer event
	@Override
	public void deleteEvent(int id) {
		Event e = eventrepository.findById(id).get();
		eventrepository.deleteById(id);

	}
	
	// 3-retourner par id
		@Override
		public void findEventById(int id) {
		    eventrepository.findById(id).get();
		}
		
		
		/*// 4-update wahda okhra simple barcha
		@Override
		public void UpdateEvent(Event event) {
			eventrepository.save(event);
		}*/
		

		//5-update2Event
		@Override
		public int updateEvent(Event e, int id) {
			 
				return eventrepository.updateEvent(e.getTitle(),e.getDate_begin(),e.getHour(),e.getDate_end(),e.getDescription(),e.getPlace(),e.getPhoto(),e.getPrice(),e.getNbr_places(),e.getType(),e.getId());
						}

	
	
	
	
	
	//8-retourner par nom
	@Override
	public Event findEventByName(String name) {
		return eventrepository.findEventByName(name);
	}

	//9-retourner event par type filter event
		@Override
		public List<Event> filterEvent(Type type) {
			return eventrepository.filterByType(type);
		}
		
		// 10- les 5 les plus visitées par rapport au nombre des vues
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
				int max = sortedList.get(sortedList.size()-1);
				int indice = listId.get(listViews.indexOf(max));
				m.put(indice, max);

				System.out.println(indice + " " + max);
				sortedList.remove(sortedList.size()-1);
				listViews.set(listViews.indexOf(max), -1);
			}
			return m;

		}
		
		//creating affectedEventUser that affect user to event
		/*@Override
		public String addParticipation(int iduser, int idevent) {
			
					Event event = eventrepository.findById(idevent).get();
			
			User u = iUserRepository.findById(iduser).get();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
		
			int number = 0;
			Participation p = new Participation();
			ParticipationPK participationPK = new ParticipationPK();
			
			List<Participation>participations = (List<Participation>) iParticipantRepository.findAll();
			for(int i=0 ; i<participations.size();i++) {
				if(participations.get(i).getEvent().getIdEvenement() == idevent && 
						participations.get(i).getUser().getIdUser() == iduser)
			
					return "You are already participate !!";
				
			}
			
			
			if(event.getPlacesNbr() >0) {
				float discPercent =(100f-event.getDiscountPercentage())/100f;
				float newPrice =event.getTicketPrice() * discPercent;// pourcentage de réduction ticket		}
			
			participationPK.setIdEvent(event.getIdEvenement());
			participationPK.setIdUser(iduser);
			p.setParticipationDate(dateFormat.format(date));	
			event.setPlacesNbr(event.getPlacesNbr() - 1);
			event.setParticipantsNbr(event.getParticipantsNbr() + 1);
			
			event.setCollAmount(event.getCollAmount()+ newPrice);
			p.setPrice(newPrice);
			p.setParticipationPK(participationPK);
			p.setParticipationDate(new Date().toString());
			
			
			iParticipantRepository.save(p);
			iEventRepository.save(event);
			}
			
			
			return "Affected successfully with discount percentage";
			
		}
		*/
		
		@Override
		public List<String> displayBestEventsByViews() {
			
			List<String>list = new ArrayList<>();
			String s ="";
			List<Integer>listId = new ArrayList<>();
			List<Integer>listViews = new ArrayList<>();
			
			List<Event> listEvent = (List<Event>)eventrepository.findAll();
			
			for(Event e : listEvent) {
				listId.add(e.getId());
				listViews.add(e.getViews());
			
			}
			
			List<Integer> sortedList = new ArrayList<>(listViews);
			
			Collections.sort(sortedList);
			
			for(int i = 0 ; i< 10 ; i++) {
				int max = sortedList.get(sortedList.size()-1);
				int ind = listId.get(listViews.indexOf(max));
				s = (i+1)+"--Event: "+eventrepository.findById(ind).get().getTitle()+"  = with  "+max+" views  ";
				list.add(s);
				sortedList.remove(sortedList.size()-1);
				listViews.set(listViews.indexOf(max), -1);
				}
			return list;
		}
		
		
		//15-affecter type à un event 	
		@Override
	    public String affecterTypeEvent(String type,int idevent){
		Event event = eventrepository.findById(idevent).get();
		String msg=" ";
		try {
			for(Type t:Type.values()){
				if(t == Type.valueOf(type)){
					event.setType(Type.valueOf(type));
					eventrepository.save(event);
					return msg= "Type affected successfully !!";
				}
			}
		}catch(Exception e){
			msg= "Failed to affect type!!";
		}
		return msg;
	}
		
	//11-evenement qui vont venir
		@Override
		public List<Event> upcomeEvents() {
			List<Event> list= eventrepository.upcomingEvents();
			
			return list;	
				}
		
	/*//12-les evenements qui ont depassé la date systeme
	@Override
	public List<Event> passedEvents(){
		List<Event> e=eventrepository.passedEvents();
		return e;
	}	*/	
		
		//16-remboursser en cas d'annulation 
		@Override
		public void refundUsers(int eid) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			java.util.Date date = new java.util.Date();
			
			Event ev = eventrepository.findById(eid).get();
			User u = new User();

			List<Participation> participationsOfEvent = participantRepository.Particiapations(ev);
			System.out.println("hiii==");
			//Notification n = new Notification();

			if(!ev.getParticipations().isEmpty()) {
			for(Participation p : participationsOfEvent) {
				//int u = new User();//user id
				u = userRepository.findById(p.getUser().getId()).get();
				float refundedAmount = p.getPrice();
				ev.setCollAmount(ev.getCollAmount()-refundedAmount);
				//userRepository.save(u);
				System.out.println("hiiiiii==");

				participantRepository.delete(p);
				
				
				eventrepository.save(ev);
				

				//n.setEvent(ev);
				//partie teb3a notification
				/*n.setUser(u);
				LocalTime localTime = LocalTime.now();

				n.setTime(Time.valueOf(localTime));
				n.setSubject("Canceled Event");
				n.setDescription("Dear "+u.getLname()+" "+u.getFname()+""
						+ ",we regret to announce that the event "+ev.getTitle()+" you want to attend has been canceled for some reason."
						+ " That's why, we have refunded your ticket price. If there is a problem, do not hesitate to contact us."
						+ " Thank you.");
				n.setDate(date);
				
				n.setStatus("Not Seen Yet");

				
				notificationRepository.save(n);
			*/
			}
			
		}else {
			System.out.println("event without participations");
		}
			
			
			
			List<Donnation>donationsResult =  donnationRepository.DonationOfEvent(ev);
			
			for(Donnation d : donationsResult) {
				User user = d.getUser();
				
				float refundedAmount = d.getAmount();// flous ta3 donation
				System.out.println("donation="+refundedAmount);
				System.out.println("jackpot before=="+ev.getJackpot());
				ev.getJackpot().setSomme(ev.getJackpot().getSomme()- refundedAmount);//fhmtha?? brbi haw jit njib 9ahwa
				System.out.println("jackpot after retrieve money=="+ev.getJackpot().getSomme());
				System.out.println("collAmount before=="+ev.getCollAmount());
				ev.setCollAmount(ev.getCollAmount()-refundedAmount);
				
				System.out.println("jackpot=="+ev.getCollAmount());

			    //System.out.println("Accurance balance before="+u.getAccBalance());
				//u.setAccBalance(u.getAccBalance()+refundedAmount);

				//System.out.println("accuranceBalance="+u.getAccBalance());
				
				//************Notification*******************//
				/*Notification notification = new Notification();
				notification.setSubject("Remoboursement");
				notification.setUser(user);
				notification.setDescription("Dear "+user.getFname()+" We annonce that the event"+ev.getTitle()+
						"was canceled for some reasons that's why we refunded your donation . for more informations do not hesitate to contact us"
						+ "thank you");
				notification.setDate(new Date());
				notification.setStatus("not seen Yet");
				donnationRepository.deleteById(d.getId());
				eventrepository.save(ev);
				userRepository.save(u);*/

			}
			
		}
			
		@Override
		public List<String> displayEventsByParticipants() {
			// TODO Auto-generated method stub
			List<Event> events = (List<Event>) eventrepository.findAll();
			List<Integer>listIdEvent = new ArrayList<>();
			List<Integer>listNbrParticipantEvent = new ArrayList<>();
			List<String>results = new ArrayList<>();
			String message = "";
			for(Event event : events) {
				listIdEvent.add(event.getId());
				listNbrParticipantEvent.add(event.getNbr_participants());	
			}
			
			List<Integer>sortListed =new ArrayList<>(listNbrParticipantEvent);
			Collections.sort(sortListed);
			
			for(int i = 0; i<5 ; i++) {
				int max = sortListed.get(sortListed.size() - 1);
				int idEventByPart = listIdEvent.get(listNbrParticipantEvent.indexOf(max));
				message ="Event "+eventrepository.findById(idEventByPart).get().getTitle()+"with "+max+" Participations";
				results.add(message);
				sortListed.remove(sortListed.size()-1);
				listNbrParticipantEvent.set(listNbrParticipantEvent.indexOf(max),-1);
			}
			
			return results;	
		}

		@Override
		public List<String> displayEventsByCollAmount() {
		// TODO Auto-generated method stub
			List<Event> events = (List<Event>) eventrepository.findAll();
			List<Integer>listIdEvent = new ArrayList<>();
			List<Float>listCollAmountEvent = new ArrayList<>();
			List<String>results = new ArrayList<>();
			String message = "";
			for(Event event : events) {
				listIdEvent.add(event.getId());
				listCollAmountEvent.add(event.getCollAmount());
			
			}
		
			List<Float>sortListed =new ArrayList<>(listCollAmountEvent);
			Collections.sort(sortListed);
		
			for(int i = 0; i<2 ; i++) {
				float max = sortListed.get(sortListed.size() - 1);//derniere valeur
				int idEventByPart = listIdEvent.get(listCollAmountEvent.indexOf(max));//retourner event qui a plus de participants
				message ="Event "+eventrepository.findById(idEventByPart).get().getTitle()+"  with  "+max+" dinars   ,  Collectd Amount  ";
				results.add(message);
				sortListed.remove(sortListed.size()-1);
				listCollAmountEvent.set(listCollAmountEvent.indexOf(max),(float) -1);
			}
		
				return results;
		
			}
		
		@Override
		public Event findbyId(int id) {
			return eventrepository.findById(id).get();
		}
		
		@Override
		public List<String> getEventTwoDatesBeetween(Date date1, Date date2) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			List<Event>events = (List<Event>) eventrepository.findAll();
			List<String>results = new ArrayList<>();
			
			if(date1.after(date2)) {
				return null;
			}
			
			for(int i = 0 ; i<events.size();i++) {
				if((events.get(i).getDate_begin().after(date1) &&( events.get(i).getDate_begin().before(date2)))) {
					//results.add(ResultEvent(events,i));
				// mte3i ena return	results.addAll(events);
				}
				
			}
			if(results.isEmpty()) {
				 results.add("Event Not Found between two dates we are sorry :( :( ");
				 return results;
			}
			return results;
		}
			
		
		
	/*	
	// 13-Ajouter particiaption à un event et user
	@Override
	public String addParticipation(int iduser, int idevent) {
		Event event = eventrepository.findById(idevent).get();
		User u= userRepository.findById(iduser).get();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date=new Date();
		int number = 0;
		Participation p = new Participation();
		ParticipationPK participationPK = new ParticipationPK();
		List<Participation>participations = (List<Participation>) participantRepository.findAll();
		for(int i=0 ; i<participations.size();i++) {
			if(participations.get(i).getEvent().getId() == idevent && participations.get(i).getUser().getId() == iduser)
				return "You have already participated in this event :)";
			
			}
		if(event.getNbr_places()>0) {
			float discPercent =(100f-event.getDiscountPercentage())/100f;
			float newPrice =event.getPrice() * discPercent;// pourcentage de réduction ticket		}
			participationPK.setIdEvent(event.getId());
			participationPK.setIdUser(iduser);
			p.setParticipationDate(dateFormat.format(date));
			event.setNbr_places(event.getNbr_places()-1);
			event.setNbr_participants(event.getNbr_participants()+1);
			event.setCollAmount(event.getCollAmount()+newPrice);
			p.setPrice(newPrice);
			p.setParticipationPK(participationPK);
			p.setParticipationDate(new Date().toString());
			participantRepository.save(p);
			eventrepository.save(event);
		}
		return "Affected successfully with discount !!";
	}
*/
}
