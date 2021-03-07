package tn.esprit.spring.service.interfaces;

import java.util.List;

import tn.esprit.spring.entity.Comment;

public interface ICommentService {

	public void addComment(Comment c);

	public void updateComment(Comment c);

	public void deleteComment(int id);

	public List<Comment> getAllComment();

}
