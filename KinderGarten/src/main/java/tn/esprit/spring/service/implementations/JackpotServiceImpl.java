package tn.esprit.spring.service.implementations;

import tn.esprit.spring.service.interfaces.IJackpotService;
@Service
public class JackpotServiceImpl implements IJackpotService {
	private static final Logger l = LogManager.getLogger(JackpotServiceImpl.class);
	@Autowired
	IJackpotRepository jackpotRepository;

	@Override
	public void AddJackpot(Jackpot jackpot) {
		jackpotRepository.save(jackpot);
		
	}

	@Override
	public void UpdateJackpot(Jackpot jackpot) {
		jackpotRepository.save(jackpot);
		
	}

	@Override
	public void DeleteJackpot(int id) {
		jackpotRepository.deleteById(id);
		
	}

	@Override
	public List<Jackpot> retreiveAllJackpot() {
			List<Jackpot> jkp= (List<Jackpot>) jackpotRepository.findAll();
			for (Jackpot j:jkp){
				l.info("Jackpot :"+ j);
			}
			return jkp;
	}

}
