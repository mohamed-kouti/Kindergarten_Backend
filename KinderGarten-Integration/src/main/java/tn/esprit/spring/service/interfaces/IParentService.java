package tn.esprit.spring.service.interfaces;

import java.util.List;

import tn.esprit.spring.entity.Child;
import tn.esprit.spring.entity.Parent;

public interface IParentService {
	

	public List<Child> getMyChild(Parent parent);
	public Parent addParent(Parent p);
	public Parent updateParent(Parent p);
}
