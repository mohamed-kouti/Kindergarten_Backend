package tn.esprit.spring.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Post;
import tn.esprit.spring.repository.IPostRepository;
import tn.esprit.spring.service.interfaces.IPostService;
@Service
public class PostServiceImpl implements IPostService {

	@Autowired
	IPostRepository postrep;

	@Override
	public void addPost(Post p) {
		postrep.save(p);

	}

	@Override
	public void updatePost(Post p) {
		postrep.save(p);

	}

	@Override
	public void deletePost(int id) {
		postrep.deleteById(id);

	}

	@Override
	public List<Post> getAllPost() {

		return (List<Post>) postrep.findAll();
	}

}
