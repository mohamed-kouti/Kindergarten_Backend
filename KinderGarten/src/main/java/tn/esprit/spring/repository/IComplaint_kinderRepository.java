package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entity.Complaint_kinder;
import tn.esprit.spring.entity.PK_SAT;

public interface IComplaint_kinderRepository extends CrudRepository<Complaint_kinder, PK_SAT> {

}
