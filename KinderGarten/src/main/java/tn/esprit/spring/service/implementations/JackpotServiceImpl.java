package tn.esprit.spring.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Jackpot;
import tn.esprit.spring.repository.IJackpotRepository;
import tn.esprit.spring.service.interfaces.IJackpotService;

@Service
public class JackpotServiceImpl implements IJackpotService {
	
	@Autowired
	IJackpotRepository jackpotService;
	

	
	@Autowired
	IJackpotRepository iJackPotService;
	
	@Override
	public Jackpot addJackpot(Jackpot jackpot) {
		Jackpot j = new Jackpot();
		j.setSomme(0);
		return jackpotService.save(j);
		
	}

	@Override
	public Jackpot findJackpot(Event event) {
		
		Jackpot jackpot = event.getJackpot();
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
