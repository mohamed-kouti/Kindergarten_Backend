package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entity.Complaint_post;
import tn.esprit.spring.entity.PK_POST;

public interface IComplaint_postRepository extends CrudRepository<Complaint_post, PK_POST> {

}
