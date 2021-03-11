package tn.esprit.spring.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.KinderGarten;
import tn.esprit.spring.repository.IKindergartenRepository;
import tn.esprit.spring.service.interfaces.IKindergartenService;

@Service
public class KindergartenServiceImpl implements IKindergartenService {
	

	
	@Autowired
	IKindergartenRepository kindergartenRepo;
	

	@Override
	public List<KinderGarten> getAllkindergarten() {
		
		return (List<KinderGarten>)  kindergartenRepo.findAll();
		}


	@Override
	public KinderGarten addKindergarten(KinderGarten kindergarten) {
		kindergartenRepo.save(kindergarten);
		return kindergarten;
	}


	@Override
	public void deleteKindergartenById(int id) {
		kindergartenRepo.deleteById(id);
		
	}


	@Override
	public KinderGarten addOrUpdateKindergarten(KinderGarten kindergarten) {
		KinderGarten k =kindergartenRepo.save(kindergarten);
		return k;
	}


	@Override
	public List<KinderGarten> getKinderGartenByName(String k) {
		return kindergartenRepo.getKindergartenByName(k);
	}


	@Override
	public List<KinderGarten> getKinderGartenByPlace(String p) {
		// TODO Auto-generated method stub
		return kindergartenRepo.getKindergartenByPlace(p);
	}
	

	
	
	//public List<KinderGarten> findByNameLike(String name){
		//return kindergartenRepo.findByNameLike("%"+name+"%");
		//}
	
	/*public List<KinderGarten> listAll(String keyword) {
		if (keyword != null) {
			return kindergartenRepo.search(keyword);
		}
		return kindergartenRepo.findAll();
	}
	*/

	
}
