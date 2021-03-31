package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Post;
@Repository
public interface IPostRepository extends CrudRepository<Post, Integer> {

}
