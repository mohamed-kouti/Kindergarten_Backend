package tn.esprit.spring.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Child;
import tn.esprit.spring.entity.Parent;
import tn.esprit.spring.repository.IChildRepository;
import tn.esprit.spring.repository.IParentRepository;
import tn.esprit.spring.service.interfaces.IParentService;

@Service
public class ParentServiceImpl implements IParentService {
 
@Autowired
IParentRepository parentRepo;
@Autowired
IChildRepository childRepo;

@Override
public List<Child> getMyChild(Parent parent) {
	// TODO Auto-generated method stub
	return childRepo.findchildByparent(parent);
	
}

@Override
public Parent addParent(Parent p) {
    parentRepo.save(p);
    return p;
}

@Override
public Parent updateParent(Parent p) {
	return parentRepo.save(p);
}


}
