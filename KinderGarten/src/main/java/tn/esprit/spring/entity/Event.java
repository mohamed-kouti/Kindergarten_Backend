package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Event implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String description;
	@Temporal(TemporalType.DATE)
	private Date date_begin;
	@Temporal(TemporalType.DATE)
	private Date date_end;
	private String photo;
	private int nbr_participants;
	private String place;
	private Type type;
	@ManyToOne
	private KinderGarten kindergarten;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
	private List<Jackpot> jackpots;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
	private List<Donnation> donnations;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate_begin() {
		return date_begin;
	}

	public void setDate_begin(Date date_begin) {
		this.date_begin = date_begin;
	}

	public Date getDate_end() {
		return date_end;
	}

	public void setDate_end(Date date_end) {
		this.date_end = date_end;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getNbr_participants() {
		return nbr_participants;
	}

	public void setNbr_participants(int nbr_participants) {
		this.nbr_participants = nbr_participants;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public KinderGarten getKindergarten() {
		return kindergarten;
	}

	public void setKindergarten(KinderGarten kindergarten) {
		this.kindergarten = kindergarten;
	}

	public List<Jackpot> getJackpots() {
		return jackpots;
	}

	public void setJackpots(List<Jackpot> jackpots) {
		this.jackpots = jackpots;
	}

	public List<Donnation> getDonnations() {
		return donnations;
	}

	public void setDonnations(List<Donnation> donnations) {
		this.donnations = donnations;
	}

}
