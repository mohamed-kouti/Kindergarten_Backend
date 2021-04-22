package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Comment;
@Repository
public interface ICommentRepository extends CrudRepository<Comment, Integer> {

	@Query(value = "select * from comment c where c.post_id= :id",nativeQuery = true)
	public List<Comment> getComments(@Param("id") int id);
	
}
