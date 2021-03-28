package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entity.ComplainsByKinderGarten;
import tn.esprit.spring.entity.ComplainsByParent;
import tn.esprit.spring.entity.ComplainsByPost;
import tn.esprit.spring.entity.Complaint_kinder;
import tn.esprit.spring.entity.KinderGarten;

public interface IComplaint_kinderRepository extends CrudRepository<Complaint_kinder, Integer> {
	
	@Query("select new tn.esprit.spring.entity.ComplainsByKinderGarten( k,c) from Complaint_kinder c inner join KinderGarten k on c.garten = k.id group by c.garten")
	public List<ComplainsByKinderGarten> join();
	@Query("select new tn.esprit.spring.entity.ComplainsByPost( p,c) from Complaint_post c inner join Post p on c.post = p.id group by c.post")
	public List<ComplainsByPost> complaintByPost();
	
	
	@Query("select new tn.esprit.spring.entity.ComplainsByParent( p,c) from Complaint_kinder c inner join Parent p on c.parent = p.id group by c.parent")
	public List<ComplainsByParent> complaintByParent();
	
	public List<Complaint_kinder> findByGarten(KinderGarten KinderGarten);
}
