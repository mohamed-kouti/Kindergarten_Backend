package tn.esprit.spring.service.implementations;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Jackpot;
import tn.esprit.spring.repository.IJackpotRepository;
import tn.esprit.spring.service.interfaces.IJackpotService;
@Service
public class JackpotServiceImpl implements IJackpotService {
	private static final Logger l = LogManager.getLogger(JackpotServiceImpl.class);
	@Autowired
	IJackpotRepository jackpotRepository;

	
	//ajouter jackpot avec somme initialisée à 0
	@Override
	public Jackpot AddJackpot(Jackpot jackpot) {
		Jackpot j= new Jackpot();
		j.setSomme(0);
		 return jackpotRepository.save(j);
		
	}
	
   //retourner jackpot d'un event en question
	@Override
	public Jackpot findJackpot(Event event){
		Jackpot jackpot=event.getJackpot();
		return jackpot;
	}
	
	/*@Override
	public void UpdateJackpot(Jackpot jackpot) {
		jackpotRepository.save(jackpot);
		
	}

	@Override
	public void DeleteJackpot(int id) {
		jackpotRepository.deleteById(id);
		
	}*/

	/*@Override
	public List<Jackpot> retreiveAllJackpot() {
			List<Jackpot> jkp= (List<Jackpot>) jackpotRepository.findAll();
			for (Jackpot j:jkp){
				l.info("Jackpot :"+ j);
			}
			return jkp;
	}*/

}
