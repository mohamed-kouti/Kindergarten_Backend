package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String fname;
	private String lname;
	private String email;
	private String password;
	private String photo;
	@Temporal(TemporalType.DATE)
	private Date datebirth;
	private boolean actif;
	//added by sonia
	@Column(name="acc_balance")
	private float accBalance;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Message> messages;
	
	@OneToMany(mappedBy = "user")
	private List<Complaint_post> complaint_posts;

	//j'ai besoin de cette relation avec la table participation
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "user")
	private Set<Participation> participations;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private List<Donnation> donnations;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getDatebirth() {
		return datebirth;
	}

	public void setDatebirth(Date datebirth) {
		this.datebirth = datebirth;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	

	public float getAccBalance() {
		return accBalance;
	}

	public void setAccBalance(float accBalance) {
		this.accBalance = accBalance;
	}

	public List<Complaint_post> getComplaint_posts() {
		return complaint_posts;
	}

	public void setComplaint_posts(List<Complaint_post> complaint_posts) {
		this.complaint_posts = complaint_posts;
	}

	
	//getter setter notification et participation
	public Set<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(Set<Participation> participations) {
		this.participations = participations;
	}

	public List<Donnation> getDonnations() {
		return donnations;
	}

	public void setDonnations(List<Donnation> donnations) {
		this.donnations = donnations;
	}
	
}
