package tn.esprit.spring.service.implementations;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Post;
import tn.esprit.spring.repository.IMessageRepository;
import tn.esprit.spring.repository.IPostRepository;
import tn.esprit.spring.service.interfaces.IPostService;
@Service
public class PostServiceImpl implements IPostService {

	@Autowired
	IPostRepository postrep;
	@Autowired
	IMessageRepository messagerep;

	@Override
	public void addPost(Post p) {
		String ch = p.getContent();
		List<String> words = messagerep.getAllWord();
		for (int i = 0; i < words.size(); i++) {
			// System.out.println(words.get(i));
			if (ch.toUpperCase().contains(words.get(i).toUpperCase())) {

				ch = ch.toUpperCase().replaceAll(words.get(i).toUpperCase(), "****");

			}

		}
		p.setContent(ch);
		p.setDate_post(java.sql.Date.valueOf(LocalDate.now()));
		
		
		postrep.save(p);

	}

	@Override
	public void updatePost(Post p) {
		String ch = p.getContent();
		List<String> words = messagerep.getAllWord();
		for (int i = 0; i < words.size(); i++) {
			// System.out.println(words.get(i));
			if (ch.toUpperCase().contains(words.get(i).toUpperCase())) {

				ch = ch.toUpperCase().replaceAll(words.get(i).toUpperCase(), "****");

			}

		}
		p.setContent(ch);
		p.setDate_post(java.sql.Date.valueOf(LocalDate.now()));
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

	@Override
	public Post getPostbyId(int id) {
		return postrep.findById(id).get();
	}
	@Override
	public void updateLike(int id) {
		postrep.updateLike(id);
	}
	
	@Override
	public void updateDislike(int id) {
		postrep.updatedDislike(id);
	}
}
