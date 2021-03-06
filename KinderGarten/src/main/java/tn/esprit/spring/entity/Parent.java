package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Parent extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean delegate;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
	private List<Child> childs;
	@OneToMany(mappedBy = "parent")
	private List<Satisfaction> satisfactions;
	@OneToMany(mappedBy = "parent")
	private List<Complaint_kinder> complaint_kinders;
	@OneToMany(mappedBy = "parent")
	private List<Appointment> appointments;

	public boolean isDelegate() {
		return delegate;
	}

	public void setDelegate(boolean delegate) {
		this.delegate = delegate;
	}

	public List<Child> getChilds() {
		return childs;
	}

	public void setChilds(List<Child> childs) {
		this.childs = childs;
	}

	public List<Satisfaction> getSatisfactions() {
		return satisfactions;
	}

	public void setSatisfactions(List<Satisfaction> satisfactions) {
		this.satisfactions = satisfactions;
	}

	public List<Complaint_kinder> getComplaint_kinders() {
		return complaint_kinders;
	}

	public void setComplaint_kinders(List<Complaint_kinder> complaint_kinders) {
		this.complaint_kinders = complaint_kinders;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

}
