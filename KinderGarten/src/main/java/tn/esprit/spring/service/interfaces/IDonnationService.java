package tn.esprit.spring.service.interfaces;

import java.util.List;

import tn.esprit.spring.entity.Donnation;
import tn.esprit.spring.entity.Event;

public interface IDonnationService {
	public String Donnation(int eid,int uid, float amount);
	public List<Donnation> getDonationEvention(Event event) ;
	public List<Donnation> getHistoryDonation();
}
