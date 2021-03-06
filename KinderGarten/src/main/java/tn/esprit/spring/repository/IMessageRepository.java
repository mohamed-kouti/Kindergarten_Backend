package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entity.Message;

public interface IMessageRepository extends CrudRepository<Message, Integer> {

}
