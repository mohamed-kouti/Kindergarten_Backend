package tn.esprit.spring.service.interfaces;

import java.util.List;

import tn.esprit.spring.entity.Jackpot;

public interface IJackpotService {

	public void AddJackpot(Jackpot jackpot);
	public void UpdateJackpot(Jackpot jackpot);
	public void DeleteJackpot(int id);
	public List<Jackpot> retreiveAllJackpot();
}
