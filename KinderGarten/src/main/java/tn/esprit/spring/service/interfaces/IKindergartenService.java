package tn.esprit.spring.service.interfaces;

import java.util.List;

import tn.esprit.spring.entity.KinderGarten;


public interface IKindergartenService {
	
	public List<KinderGarten> getAllkindergarten();

	public KinderGarten addKindergarten(KinderGarten kindergarten);
	
	public void deleteKindergartenById(int id);
	
	public KinderGarten addOrUpdateKindergarten(KinderGarten kindergarten);
	
	
		

}
