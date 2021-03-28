package tn.esprit.spring.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entity.KinderGarten;
import tn.esprit.spring.entity.KinderGartenOpeningDay;

public interface IKinderGartenOpeningDayRepository extends CrudRepository<KinderGartenOpeningDay, Integer> {
	List<KinderGartenOpeningDay> findByKinderGarten(KinderGarten kinderGarten);
}