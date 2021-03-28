package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class KinderGartenOpeningDay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -849364791549014601L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.STRING)
	private EDaysOfWeek day;

	@Temporal(TemporalType.TIME)
	private Date timeOpen;

	@Temporal(TemporalType.TIME)
	private Date timeClose;

	@ManyToOne
	@JoinColumn(name = "kinderGarten", referencedColumnName = "id")
	private KinderGarten kinderGarten;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EDaysOfWeek getDay() {
		return day;
	}

	public void setDay(EDaysOfWeek day) {
		this.day = day;
	}

	public Date getTimeOpen() {
		return timeOpen;
	}

	public void setTimeOpen(Date timeOpen) {
		this.timeOpen = timeOpen;
	}

	public Date getTimeClose() {
		return timeClose;
	}

	public void setTimeClose(Date timeClose) {
		this.timeClose = timeClose;
	}

	public KinderGarten getKinderGarten() {
		return kinderGarten;
	}

	public void setKinderGarten(KinderGarten kinderGarten) {
		this.kinderGarten = kinderGarten;
	}

}

