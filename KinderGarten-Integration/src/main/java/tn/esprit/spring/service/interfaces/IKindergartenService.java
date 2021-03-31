package tn.esprit.spring.service.interfaces;

import java.util.List;
import tn.esprit.spring.entity.KinderGarten;
import tn.esprit.spring.entity.Classroom;


public interface IKindergartenService {
	
	public List<KinderGarten> getAllkindergarten();

	public KinderGarten addKindergarten(KinderGarten kindergarten);
	
	public void deleteKindergartenById(int id);
	
	public KinderGarten addOrUpdateKindergarten(KinderGarten kindergarten);
	public List<KinderGarten> getKinderGartenByName(String k);
	
	public List<KinderGarten> getKinderGartenByPlace(String k);
	
	
	public KinderGarten getKinderGById(int id);
	
	public List<String> displayBestKinderGartensByViews();
	
    public Double getRevenuePerYearBykinder(int id,String year);
    public List<Classroom> getClassesByKinderg(int id);
    public List<KinderGarten> chercherParZone(Double longi, Double lat,Double rayon);
    
    
	
		

}
