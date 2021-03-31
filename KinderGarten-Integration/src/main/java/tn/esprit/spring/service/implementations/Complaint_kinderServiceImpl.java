package tn.esprit.spring.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Complaint_kinder;
import tn.esprit.spring.entity.KinderGarten;
import tn.esprit.spring.repository.IComplaint_kinderRepository;
import tn.esprit.spring.repository.IKindergartenRepository;
import tn.esprit.spring.service.interfaces.IComplaint_kinderService;

@Service
public class Complaint_kinderServiceImpl implements IComplaint_kinderService {
	
	@Autowired
	IComplaint_kinderRepository iComplaint_kinderRepository;
	
	@Autowired
	IKindergartenRepository iKindergartenRepository;
	
	@Override
	public KinderGarten banKinderGarten(KinderGarten kinderGarten) {
		
		
		List<Complaint_kinder> complaint_kinder =  iComplaint_kinderRepository.findByGarten(kinderGarten);
		
		int complaintNumber = complaint_kinder.size();
		
		if(complaintNumber >= 5) {
			iKindergartenRepository.save(kinderGarten);
			
		}
			
				
		return kinderGarten;
	}

}
