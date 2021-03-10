package tn.esprit.spring.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Child;
import tn.esprit.spring.entity.Classroom;
import tn.esprit.spring.repository.IChildRepository;
import tn.esprit.spring.repository.IClassroomRepository;
import tn.esprit.spring.service.interfaces.IChildService;

@Service
public class ChildServiceImpl implements IChildService {
	
	@Autowired
	IChildRepository childRepo ;
	@Autowired
	IClassroomRepository classeRepo;
	
	@Override
	public Child addChild(Child c) {
		childRepo.save(c);
		return c;
	}
	@Override
	public void deleteChild(int id) {
		childRepo.deleteById(id);
		
	}
	@Override
	public List<Child> getAllChildren() {
		return (List<Child>)  childRepo.findAll() ;
		
	}
	@Override
	public Child updateChild(int id, Child c) {
		
		return childRepo.save(c);
	}
	@Override
	public Child getChild(int id) {
		return childRepo.findById(id).get();
	}

}
