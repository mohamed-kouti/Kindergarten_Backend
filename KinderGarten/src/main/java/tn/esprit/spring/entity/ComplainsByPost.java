package tn.esprit.spring.entity;

import java.io.Serializable;

public class ComplainsByPost implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8939580962899134562L;
	
	private Post post;
	private Complaint_post complaint_post;
	

	public ComplainsByPost(Post post, Complaint_post complaint_post) {
		super();
		this.post = post;
		this.complaint_post = complaint_post;
	}
	public Complaint_post getComplaint_post() {
		return complaint_post;
	}
	public void setComplaint_post(Complaint_post complaint_post) {
		this.complaint_post = complaint_post;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	
	

}
