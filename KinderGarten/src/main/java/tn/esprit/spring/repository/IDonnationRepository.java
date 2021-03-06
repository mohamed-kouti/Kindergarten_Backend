package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entity.Donnation;

public interface IDonnationRepository extends CrudRepository<Donnation, Integer> {

}
