package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entity.Event;

public interface IEventRepository extends CrudRepository<Event, Integer> {

}
