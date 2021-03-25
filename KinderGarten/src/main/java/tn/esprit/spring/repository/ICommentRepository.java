package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Comment;
@Repository
public interface ICommentRepository extends CrudRepository<Comment, Integer> {

}
