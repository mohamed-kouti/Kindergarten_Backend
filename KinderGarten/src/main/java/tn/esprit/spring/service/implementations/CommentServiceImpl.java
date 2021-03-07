package tn.esprit.spring.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.repository.ICommentRepository;
import tn.esprit.spring.service.interfaces.ICommentService;

@Service
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
	public void deleteComment(int id) {
		commentrep.deleteById(id);

	}

	@Override
	public List<Comment> getAllComment() {

		return (List<Comment>) commentrep.findAll();
	}

}
