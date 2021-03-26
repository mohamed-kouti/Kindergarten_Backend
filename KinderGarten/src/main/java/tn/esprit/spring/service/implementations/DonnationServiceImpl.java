package tn.esprit.spring.service.implementations;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Donnation;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Jackpot;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.IDonnationRepository;
import tn.esprit.spring.repository.IEventRepository;
import tn.esprit.spring.repository.IJackpotRepository;
import tn.esprit.spring.repository.IUserRepository;
import tn.esprit.spring.service.interfaces.IDonnationService;

@Service

public class DonnationServiceImpl implements IDonnationService {
	private static final Logger l = LogManager.getLogger(EventServiceImpl.class);
	
	@Autowired
	IDonnationRepository donnationrepository;
	
	@Autowired
	IJackpotRepository jackpotrepository;
	
	@Autowired
	IEventRepository eventrepository;
	
	@Autowired
	IUserRepository userrepository;
	
	
	//Donation lel event w kol event endo jackpot w kol jackpot fiha somme donation
    @Override
		public String Donnation(int eid,int uid, float amount) {
			float totale=0;
			float newCollAmount=0;
			Donnation donnation = new Donnation();
			Event ev = eventrepository.findById(eid).get();
			User user = userrepository.findById(uid).get();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			//attribut static  dans l'user 
			Jackpot jackpot = new Jackpot();
			System.out.println(ev.getJackpot());
			if(user.getAccBalance() >=amount) {
				user.setAccBalance(user.getAccBalance() - amount);
				Jackpot j = jackpotrepository.findJackpotEvent(ev.getId());
				j.setSomme(j.getSomme() + amount);
				ev.setCollAmount(ev.getCollAmount() + amount);
				donnation.setAmount(amount);
				donnation.setContributionDate(dateFormat.format(date));
				donnation.setEvent(ev);
				donnation.setUser(user);
				userrepository.save(user);
				donnationrepository.save(donnation);
				jackpotrepository.save(j);
				eventrepository.save(ev);
				totale = user.getAccBalance() - amount;
				jackpot = eventrepository.findJackpot(ev.getJackpot());
				System.out.println("sum="+jackpot.getSomme());
				donnation.setAmount(amount);
				donnation.setContributionDate(dateFormat.format(date));
				donnation.setEvent(ev);
				donnation.setUser(user);
				jackpot.setSomme(jackpot.getSomme()+amount);
				jackpotrepository.save(jackpot);
				eventrepository.save(ev);
	            donnationrepository.save(donnation);
				
				
				return "Donation saved successfully!!";
			}
			return "Balance amount below than amount of donation we are sorry ";
		}

		@Override
		public List<Donnation> getDonationEvention(Event event) {
			List<Donnation> list = donnationrepository.DonationOfEvent(event);
			return list;
		}

		@Override
		public List<Donnation> getHistoryDonation() {
			return null;
			/*List<Donnation> list = donnationrepository.DonnationOfUser(AdminController.USERCONNECTED);
			return list;*/
		}


	}
	