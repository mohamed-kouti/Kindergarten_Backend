package tn.esprit.spring.service.implementations;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.repository.ICommentRepository;
import tn.esprit.spring.repository.IMessageRepository;
import tn.esprit.spring.service.interfaces.ICommentService;

@Service
public class CommentServiceImpl implements ICommentService {

	@Autowired
	ICommentRepository commentrep;
	@Autowired
	IMessageRepository messagerep;

	@Override
	public void addComment(Comment c) {
		String ch = c.getComment();
		List<String> words = messagerep.getAllWord();
		for (int i = 0; i < words.size(); i++) {
			// System.out.println(words.get(i));
			if (ch.toUpperCase().contains(words.get(i).toUpperCase())) {
				ch = ch.toUpperCase().replaceAll(words.get(i).toUpperCase(), "****");
			}
		}
		c.setComment(ch);
		c.setDate_com(java.sql.Date.valueOf(LocalDate.now()));
		commentrep.save(c);

	}

	@Override
	public void updateComment(Comment c) {
		commentrep.save(c);

	}

	@Override
	public void deleteComment(int id) {
		commentrep.deleteById(id);

	}

	@Override
	public List<Comment> getAllComment() {

		return (List<Comment>) commentrep.findAll();
	}

}
