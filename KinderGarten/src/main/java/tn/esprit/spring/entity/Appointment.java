package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Appointment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "id_parent", referencedColumnName = "id")
	private Parent parent;
	@ManyToOne
	@JoinColumn(name = "id_kindergarten_owner", referencedColumnName = "id")
	private KinderGarten_owner garten_owner;

	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_app;
	
	@Temporal(TemporalType.TIME)
	private Date startTime;

	@Temporal(TemporalType.TIME)
	private Date endTime;
	


	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public KinderGarten_owner getGarten_owner() {
		return garten_owner;
	}

	public void setGarten_owner(KinderGarten_owner garten_owner) {
		this.garten_owner = garten_owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate_app() {
		return date_app;
	}

	public void setDate_app(Date date_app) {
		this.date_app = date_app;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	
}
