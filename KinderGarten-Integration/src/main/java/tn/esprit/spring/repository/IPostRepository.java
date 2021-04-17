package tn.esprit.spring.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Post;
@Repository
public interface IPostRepository extends CrudRepository<Post, Integer> {
	@Transactional
	@Modifying
	@Query(value="update post p set p.nbr_like=p.nbr_like+1 where p.id= :id",nativeQuery = true)
	void updateLike(@Param("id")int id);
	@Transactional
	@Modifying
	@Query(value="update post p set p.nbr_dislike=p.nbr_dislike+1 where p.id= :id",nativeQuery = true)
	void updatedDislike(@Param("id")int id);

}
