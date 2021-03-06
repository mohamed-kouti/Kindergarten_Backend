package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entity.Comment;

public interface ICommentRepository extends CrudRepository<Comment, Integer> {

}
