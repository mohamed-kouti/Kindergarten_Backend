package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Complaint_post implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PK_POST pk_post;
	@ManyToOne
	@JoinColumn(name = "id_user", referencedColumnName = "id", insertable = false, updatable = false)
	private User user;
	@ManyToOne
	@JoinColumn(name = "id_post", referencedColumnName = "id", insertable = false, updatable = false)
	private Post post;

	private String msg;
	@Temporal(TemporalType.DATE)
	private Date date_comp;
	private boolean stat;

	public PK_POST getPk_post() {
		return pk_post;
	}

	public void setPk_post(PK_POST pk_post) {
		this.pk_post = pk_post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getDate_comp() {
		return date_comp;
	}

	public void setDate_comp(Date date_comp) {
		this.date_comp = date_comp;
	}

	public boolean isStat() {
		return stat;
	}

	public void setStat(boolean stat) {
		this.stat = stat;
	}

}
