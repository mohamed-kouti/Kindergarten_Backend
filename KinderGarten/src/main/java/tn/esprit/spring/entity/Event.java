package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	@Column(name = "title")
	private String title;
	@Temporal(TemporalType.DATE)
	private Date date_begin;
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(style = "hh:mm")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm")
	private Date hour;
	@Temporal(TemporalType.DATE)
	private Date date_end;
	@Column(name = "description")
	private String description;
	// adresse ou se deroulera l'evenement
	@Column(name = "place")
	private String place;
	@Column(name = "photo")
	@Lob
	private byte[] photo;
	// prix du ticket
	@Column(name = "Price")
	private float Price;
	@Column(name = "collAmount")
	private float collAmount;
	// nombre des participants
	@Column(name = "nbr_participants")
	private int nbr_participants;
	// nombre des places disponibles
	@Column(name = "Nbr_places")
	private int Nbr_places;
	private boolean earlyParticipants;
	private int nbrTicketEarlyParticipants;
	// liste des types d'évenements organisés par JE
	@Enumerated(EnumType.STRING)
	private Type type;
	private int views;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
	private Set<Participation> participations;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Jackpot jackpot;
	private float discountPercentage;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event", fetch = FetchType.EAGER)
	private List<Donnation> donnation;
	
	public Event() {
		super();
	}

	
	public Event(int id, String title, Date date_begin, Date hour, Date date_end, String description, String place,
			byte[] photo, float price, float collAmount, int nbr_participants, int nbr_places,
			boolean earlyParticipants, int nbrTicketEarlyParticipants, Type type, int views,
			Set<Participation> participations, Jackpot jackpot,float discountPercentage, List<Donnation> donnation) {
		super();
		this.id = id;
		this.title = title;
		this.date_begin = date_begin;
		this.hour = hour;
		this.date_end = date_end;
		this.description = description;
		this.place = place;
		this.photo = photo;
		Price = price;
		this.collAmount = collAmount;
		this.nbr_participants = nbr_participants;
		Nbr_places = nbr_places;
		this.earlyParticipants = earlyParticipants;
		this.nbrTicketEarlyParticipants = nbrTicketEarlyParticipants;
		this.type = type;
		this.views = views;
		this.participations = participations;
		this.jackpot = jackpot;
		this.discountPercentage = discountPercentage;
		this.donnation = donnation;
	}


	public Event(String title, Date date_begin, Date hour, Date date_end, String description, String place,
			byte[] photo, float price, float collAmount, int nbr_participants, int nbr_places,
			boolean earlyParticipants, int nbrTicketEarlyParticipants, Type type, int views,
			Set<Participation> participations, Jackpot jackpot, float discountPercentage, List<Donnation> donnation) {
		super();
		this.title = title;
		this.date_begin = date_begin;
		this.hour = hour;
		this.date_end = date_end;
		this.description = description;
		this.place = place;
		this.photo = photo;
		Price = price;
		this.collAmount = collAmount;
		this.nbr_participants = nbr_participants;
		Nbr_places = nbr_places;
		this.earlyParticipants = earlyParticipants;
		this.nbrTicketEarlyParticipants = nbrTicketEarlyParticipants;
		this.type = type;
		this.views = views;
		this.participations = participations;
		this.jackpot = jackpot;
		this.discountPercentage = discountPercentage;
		this.donnation = donnation;
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

	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
		this.hour = hour;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public float getPrice() {
		return Price;
	}

	public void setPrice(float price) {
		Price = price;
	}

	public float getCollAmount() {
		return collAmount;
	}

	public void setCollAmount(float collAmount) {
		this.collAmount = collAmount;
	}

	public int getNbr_participants() {
		return nbr_participants;
	}

	public void setNbr_participants(int nbr_participants) {
		this.nbr_participants = nbr_participants;
	}

	public int getNbr_places() {
		return Nbr_places;
	}

	public void setNbr_places(int nbr_places) {
		Nbr_places = nbr_places;
	}

	public boolean isEarlyParticipants() {
		return earlyParticipants;
	}

	public void setEarlyParticipants(boolean earlyParticipants) {
		this.earlyParticipants = earlyParticipants;
	}

	public int getNbrTicketEarlyParticipants() {
		return nbrTicketEarlyParticipants;
	}

	public void setNbrTicketEarlyParticipants(int nbrTicketEarlyParticipants) {
		this.nbrTicketEarlyParticipants = nbrTicketEarlyParticipants;
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

	public Set<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(Set<Participation> participations) {
		this.participations = participations;
	}

	public Jackpot getJackpot() {
		return jackpot;
	}

	public void setJackpot(Jackpot jackpot) {
		this.jackpot = jackpot;
	}

	public float getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(float discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public List<Donnation> getDonnation() {
		return donnation;
	}

	public void setDonnation(List<Donnation> donnation) {
		this.donnation = donnation;
	}


	@Override
	public String toString() {
		return "Event [id=" + id + ", title=" + title + ", date_begin=" + date_begin + ", hour=" + hour + ", date_end="
				+ date_end + ", description=" + description + ", place=" + place + ", photo=" + Arrays.toString(photo)
				+ ", Price=" + Price + ", collAmount=" + collAmount + ", nbr_participants=" + nbr_participants
				+ ", Nbr_places=" + Nbr_places + ", earlyParticipants=" + earlyParticipants
				+ ", nbrTicketEarlyParticipants=" + nbrTicketEarlyParticipants + ", type=" + type + ", views=" + views
				+ ", participations=" + participations + ", jackpot=" + jackpot +", discountPercentage=" + discountPercentage + ", donnation=" + donnation + "]";
	}

	
	
}