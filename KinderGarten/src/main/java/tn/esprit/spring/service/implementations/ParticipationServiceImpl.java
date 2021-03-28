package tn.esprit.spring.service.implementations;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Participation;
import tn.esprit.spring.entity.ParticipationPK;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.IEventRepository;
import tn.esprit.spring.repository.IParticipantRepository;
import tn.esprit.spring.repository.IUserRepository;
import tn.esprit.spring.service.interfaces.IParticipationService;

@Service
public class ParticipationServiceImpl implements IParticipationService{

	@Autowired
	EventServiceImpl eventServiceImpl;
	@Autowired
	IParticipantRepository ParticipationRepository;
	@Autowired
	IEventRepository EventRepository;
	@Autowired
	IUserRepository UserRepository;
	
	@Override
	public String addParticipation(int iduser, int idevent) {

		Event event = EventRepository.findById(idevent).get();
		User user = UserRepository.findById(iduser).get();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		int number = 0;
		Participation p = new Participation();
		ParticipationPK participationPK = new ParticipationPK();
		List<Participation>participations = (List<Participation>) ParticipationRepository.findAll();
		for(int i=0 ; i<participations.size();i++) {
			if(participations.get(i).getEvent().getId() == idevent && participations.get(i).getUser().getId() == iduser)
		
				return "You are already participate !!";
			
		}
		
		if(event.getNbr_participants() > 0) {
			
			System.out.println("/////---"+participations.size()+", "+event.getNbrTicketEarlyParticipants());
			if(event.isEarlyParticipants() == true && participations.size()<event.getNbrTicketEarlyParticipants()) {

				float discPercent =(100f-event.getDiscountPercentage())/100f; 
				float newPrice =event.getPrice() * discPercent;
			
			participationPK.setIdEvent(event.getId());
			participationPK.setIdUser(iduser);
			p.setParticipationDate(dateFormat.format(date));	
			event.setNbr_participants(event.getNbr_participants() - 1);
		    event.setNbr_participants(event.getNbr_participants() + 1);
		    System.out.println("coll amount =="+event.getCollAmount()+", newPrice="+newPrice);
			event.setCollAmount(event.getCollAmount()+ newPrice);
			user.setAccBalance(user.getAccBalance()-newPrice);
			p.setPrice(newPrice);
			p.setParticipationPK(participationPK);
			//participationPK.setNumber(number);
		    //p.setParticipationDate(new Date());
			ParticipationRepository.save(p);
			UserRepository.save(user);
		
			}	
			else {
				participationPK.setIdEvent(event.getId());
				participationPK.setIdUser(iduser);
				p.setParticipationDate(dateFormat.format(date));	
				event.setNbr_participants(event.getNbr_participants() - 1);
			    event.setNbr_participants(event.getNbr_participants() + 1);
			    System.out.println("coll amount =="+event.getCollAmount()+", newPrice="+event.getPrice());
				event.setCollAmount(event.getCollAmount()+ event.getPrice());
				user.setAccBalance(user.getAccBalance()-event.getPrice());

				p.setPrice(event.getPrice());
				p.setParticipationPK(participationPK);
				//participationPK.setNumber(number);
				//p.setParticipationDate(new Date());
				
				
				ParticipationRepository.save(p);

			UserRepository.save(user);
		}
		return "Affected successfully with discount percentage";
		
	}
	return "Event places is full";
	}

		@Override
		public List<Participation> participationsList() {
			List<Participation> list = (List<Participation>) ParticipationRepository.findAll();
			return list;
	}


		@Override
		public List<Participation> myParticipations() {
			// TODO Auto-generated method stub
			return null;
		}

}
