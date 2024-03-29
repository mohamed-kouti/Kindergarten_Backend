package tn.esprit.spring.service.interfaces;

import java.util.List;

import tn.esprit.spring.entity.Post;

public interface IPostService {

	public void addPost(Post p);

	public void updatePost(Post p);

	public void deletePost(int id);

	public List<Post> getAllPost();

	Post getPostbyId(int id);

	void updateLike(int id);

	void updateDislike(int id);

}
