package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entity.PK_SAT;
import tn.esprit.spring.entity.Satisfaction;

public interface ISatisfactionRepository extends CrudRepository<Satisfaction, PK_SAT> {

}
