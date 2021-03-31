package tn.esprit.spring.service.interfaces;

import java.util.List;

import tn.esprit.spring.entity.Donnation;
import tn.esprit.spring.entity.Event;

public interface IDonnationService {
	//1-creating put mapping that updates the event detail   
	public String Donnation(int eid,int uid, float amount);
	
	//2-retourner la listes des donnation d'un event
	public List<Donnation> getDonationEvention(Event event) ;
	
	
	public List<Donnation> getHistoryDonation();
}
