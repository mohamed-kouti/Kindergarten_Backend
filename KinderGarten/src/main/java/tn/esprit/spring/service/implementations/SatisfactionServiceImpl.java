package tn.esprit.spring.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Satisfaction;
import tn.esprit.spring.repository.ISatisfactionRepository;
import tn.esprit.spring.service.interfaces.ISatisfactionService;

@Service
public class SatisfactionServiceImpl implements ISatisfactionService {

	@Autowired
	ISatisfactionRepository satrep;

	@Override
	public void add_Satisfaction(Satisfaction s) {
		satrep.save(s);

	}
	

	@Override
	public List<Satisfaction> getAllSatisfaction(){
		return (List<Satisfaction>) satrep.findAll();
	}

}
