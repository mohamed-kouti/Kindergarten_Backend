package tn.esprit.spring.service.interfaces;

import java.util.List;

import tn.esprit.spring.entity.Donnation;



public interface IDonnationService {
    public void AddJDonnation(Donnation donnation);
	public void UpdateDonnation(Donnation donnation);
	public void DeleteDonnation(int id);
	public List<Donnation> retreiveAllDonnation();
}
