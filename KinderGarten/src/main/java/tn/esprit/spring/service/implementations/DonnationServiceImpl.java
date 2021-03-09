package tn.esprit.spring.service.implementations;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Donnation;
import tn.esprit.spring.repository.IDonnationRepository;
import tn.esprit.spring.service.interfaces.IDonnationService;

@Service

public class DonnationServiceImpl implements IDonnationService {
	private static final Logger l = LogManager.getLogger(EventServiceImpl.class);
	@Autowired
	IDonnationRepository donnationrepository;
	@Override
	public void AddJDonnation(Donnation donnation) {
		donnationrepository.save(donnation);
		
	}
	@Override
	public void UpdateDonnation(Donnation donnation) {
		donnationrepository.save(donnation);
		
	}
	@Override
	public void DeleteDonnation(int id) {
		donnationrepository.deleteById(id);
		
	}
	@Override
	public List<Donnation> retreiveAllDonnation() {
		List<Donnation> don= (List<Donnation>) donnationrepository.findAll();
		for (Donnation d:don){
			l.info("Donnation :"+ d);
		}
		return don;
	}
	
}
