package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Event implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "tite")
	private String title;
	@Temporal(TemporalType.DATE)
	private Date date_begin;
	@Temporal(TemporalType.DATE)
	private Date date_end;
	@Column(name = "description")
	private String description;
	// nombre des participants
	@Column(name = "nbr_participants")
	private int nbr_participants;
	// adresse ou se deroulera l'evenement
	@Column(name = "place")
	private String place;
	@Column(name = "photo")
	private String photo;
	// prix du ticket
	@Column(name = "Price")
	private float Price;
	// nombre des places disponibles
	@Column(name = "Nbr_places")
	private int Nbr_places;
	// liste des types d'évenements organisés par JE
	@Enumerated(EnumType.STRING)
	private Type type;
	private int views;

	@ManyToOne
	private KinderGarten kindergarten;
	/*
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "event") private
	 * List<Jackpot> jackpots;
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Jackpot jackpot;
	/*
	 * @JsonIgnore
	 * 
	 * @OneToMany(cascade= CascadeType.ALL, mappedBy="event", fetch=
	 * FetchType.EAGER) private Set<Notification> notifications;
	 */
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event",fetch= FetchType.EAGER)
	private List<Donnation> donnations;
	
	public Event(int id, String title, Date date_begin, Date date_end, String description,
			int nbr_participants, String place, String photo, float price, int nbr_places, Type type, int views,
			KinderGarten kindergarten, Jackpot jackpot, List<Donnation> donnations) {
		super();
		this.id = id;
		this.title = title;
		this.date_begin = date_begin;
		this.date_end = date_end;
		this.description = description;
		this.nbr_participants = nbr_participants;
		this.place = place;
		this.photo = photo;
		this.Price = price;
		this.Nbr_places = nbr_places;
		this.type = type;
		this.views = views;
		this.kindergarten = kindergarten;
		this.jackpot = jackpot;
		this.donnations = donnations;
	}

	public Event(String title, Date date_begin, Date date_end, String description, int nbr_participants,
			String place, String photo, float price, int nbr_places, Type type, int views, KinderGarten kindergarten,
			Jackpot jackpot, List<Donnation> donnations) {
		super();
		this.title = title;
		this.date_begin = date_begin;
		this.date_end = date_end;
		this.description = description;
		this.nbr_participants = nbr_participants;
		this.place = place;
		this.photo = photo;
		this.Price = price;
		this.Nbr_places = nbr_places;
		this.type = type;
		this.views = views;
		this.kindergarten = kindergarten;
		this.jackpot = jackpot;
		this.donnations = donnations;
	}

	public Event() {
		super();
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public float getPrice() {
		return Price;
	}

	public void setPrice(float price) {
		Price = price;
	}

	public int getNbr_places() {
		return Nbr_places;
	}

	public void setNbr_places(int nbr_places) {
		Nbr_places = nbr_places;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public KinderGarten getKindergarten() {
		return kindergarten;
	}

	public void setKindergarten(KinderGarten kindergarten) {
		this.kindergarten = kindergarten;
	}

	public Jackpot getJackpot() {
		return jackpot;
	}

	public void setJackpot(Jackpot jackpot) {
		this.jackpot = jackpot;
	}

	public List<Donnation> getDonnations() {
		return donnations;
	}

	public void setDonnations(List<Donnation> donnations) {
		this.donnations = donnations;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", title=" + title + ", date_begin=" + date_begin +  ", date_end="
				+ date_end + ", description=" + description + ", nbr_participants=" + nbr_participants + ", place="
				+ place + ", photo=" + photo + ", Price=" + Price + ", Nbr_places=" + Nbr_places + ", type=" + type
				+ ", views=" + views + ", kindergarten=" + kindergarten + ", jackpot=" + jackpot + ", donnations="
				+ donnations + "]";
	}
	
}
