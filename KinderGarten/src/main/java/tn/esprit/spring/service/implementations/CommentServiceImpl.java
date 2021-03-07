package tn.esprit.spring.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.repository.ICommentRepository;
import tn.esprit.spring.service.interfaces.ICommentService;

public class CommentServiceImpl implements ICommentService {

	@Autowired
	ICommentRepository commentrep;

	@Override
	public void addComment(Comment c) {
		commentrep.save(c);

	}

	@Override
	public void updateComment(Comment c) {
		commentrep.save(c);

	}

	@Override
	public void deleteComment(Comment c) {
		commentrep.delete(c);

	}

	@Override
	public List<Comment> getAllComment() {

		return (List<Comment>) commentrep.findAll();
	}

}
