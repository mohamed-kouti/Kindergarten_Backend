package tn.esprit.spring.service.interfaces;

import java.util.List;

import tn.esprit.spring.entity.Satisfaction;

public interface ISatisfactionService {

	public void add_Satisfaction (Satisfaction s);

	List<Satisfaction> getAllSatisfaction();
}
