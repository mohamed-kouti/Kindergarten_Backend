package tn.esprit.spring.entity;

import java.io.Serializable;

public class ComplainsByKinderGarten implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8939580962899134562L;
	
	private KinderGarten kinderGarten;
	private Complaint_kinder complaint_kinder;
	
	
	public ComplainsByKinderGarten(KinderGarten kinderGarten, Complaint_kinder complaint_kinder) {
		super();
		this.kinderGarten = kinderGarten;
		this.complaint_kinder = complaint_kinder;
	}
	public KinderGarten getKinderGarten() {
		return kinderGarten;
	}
	public void setKinderGarten(KinderGarten kinderGarten) {
		this.kinderGarten = kinderGarten;
	}
	public Complaint_kinder getComplaint_kinder() {
		return complaint_kinder;
	}
	public void setComplaint_kinder(Complaint_kinder complaint_kinder) {
		this.complaint_kinder = complaint_kinder;
	}
	
	



}
