package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@DiscriminatorValue("1")
@JsonIgnoreProperties(value = {"appointments","kinder","complaint_posts","kindergarten"})
public class KinderGarten_owner extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToOne(mappedBy = "kinder")
	private KinderGarten kindergarten;
	@OneToMany(mappedBy = "garten_owner")
	private List<Appointment> appointments;

	public KinderGarten getKindergarten() {
		return kindergarten;
	}

	public void setKindergarten(KinderGarten kindergarten) {
		this.kindergarten = kindergarten;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

}
