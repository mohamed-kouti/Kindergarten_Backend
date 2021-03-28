package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class KinderGarten implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int phone;
	private float price;
	private String description;
	private String place;
	@Lob
	private byte[] logo;
	private Double revenue;
	private Double longi;
	private Double latitude;

	

	public Double getLongi() {
		return longi;
	}
	public void setLongi(Double longi) {
		this.longi = longi;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getRevenue() {
		return revenue;
	}
	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}

	private int nbr_emp;
	@Temporal(TemporalType.DATE)
	private Date date_creation;
	
	private int views;
	
	@Temporal(TemporalType.DATE)
	private Date datefinInscrit; 
	
	@OneToOne
	private KinderGarten_owner kinder;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kindergarten")
	private List<Event> events;
	@JsonIgnore
	@OneToMany(mappedBy = "garten")
	private List<Satisfaction> satisfactions;
	@JsonIgnore
	@OneToMany(mappedBy = "garten")
	private List<Complaint_kinder> complaint_kinders;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kindergarten")
	private List<Classroom> classrooms;

	public int getId() {
		return id;
	}
    public void setId(int id) {
		this.id = id;
	}
	
	public Date getDatefinInscrit() {
		return datefinInscrit;
	}
	public void setDatefinInscrit(Date datefinInscrit) {
		this.datefinInscrit = datefinInscrit;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getNbr_emp() {
		return nbr_emp;
	}

	public void setNbr_emp(int nbr_emp) {
		this.nbr_emp = nbr_emp;
	}

	public Date getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}
	

	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public KinderGarten_owner getKinder() {
		return kinder;
	}

	public void setKinder(KinderGarten_owner kinder) {
		this.kinder = kinder;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
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

	public List<Classroom> getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(List<Classroom> classrooms) {
		this.classrooms = classrooms;
	}

	@Override
	public String toString() {
		return "KinderGarten [id=" + id + ", name=" + name + ", phone=" + phone + ", price=" + price + ", description="
				+ description + ", place=" + place + ", logo=" + Arrays.toString(logo) + ", revenue=" + revenue
				+ ", nbr_emp=" + nbr_emp + ", date_creation=" + date_creation + ", views=" + views + ", datefinInscrit="
				+ datefinInscrit + ", kinder=" + kinder + ", events=" + events + ", satisfactions=" + satisfactions
				+ ", complaint_kinders=" + complaint_kinders + ", classrooms=" + classrooms + "]";
	}

}
