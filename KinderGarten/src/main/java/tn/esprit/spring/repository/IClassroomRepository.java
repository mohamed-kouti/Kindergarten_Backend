package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entity.Classroom;

public interface IClassroomRepository extends CrudRepository<Classroom, Integer> {

}
