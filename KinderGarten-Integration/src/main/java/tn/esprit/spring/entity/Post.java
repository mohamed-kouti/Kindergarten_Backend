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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String photo;
	private String content;
	@Temporal(TemporalType.DATE)
	private Date date_post;
	private int nbr_like;
	private int nbr_dislike;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
	private List<Comment> comments;

	@ManyToOne
	User user;
	@OneToMany(mappedBy = "post")
	private List<Complaint_post> complaint_posts;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getDate_post() {
		return date_post;
	}

	public void setDate_post(Date date_post) {
		this.date_post = date_post;
	}

	public int getNbr_like() {
		return nbr_like;
	}

	public void setNbr_like(int nbr_like) {
		this.nbr_like = nbr_like;
	}

	public int getNbr_dislike() {
		return nbr_dislike;
	}

	public void setNbr_dislike(int nbr_dislike) {
		this.nbr_dislike = nbr_dislike;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Complaint_post> getComplaint_posts() {
		return complaint_posts;
	}

	public void setComplaint_posts(List<Complaint_post> complaint_posts) {
		this.complaint_posts = complaint_posts;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
