package tn.esprit.spring.entity;

import java.io.Serializable;

public class ComplainsByParent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8939580962899134562L;
	
	private Parent parent;
	private Complaint_kinder complaint_kinder;
	
	
	
	public ComplainsByParent(Parent parent, Complaint_kinder complaint_kinder) {
		super();
		this.parent = parent;
		this.complaint_kinder = complaint_kinder;
	}
	public Complaint_kinder getComplaint_kinder() {
		return complaint_kinder;
	}
	public void setComplaint_kinder(Complaint_kinder complaint_kinder) {
		this.complaint_kinder = complaint_kinder;
	}
	public Parent getParent() {
		return parent;
	}
	public void setParent(Parent parent) {
		this.parent = parent;
	}
	
	
	


}
