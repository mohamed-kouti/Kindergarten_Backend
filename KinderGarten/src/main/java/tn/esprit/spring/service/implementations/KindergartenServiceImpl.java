package tn.esprit.spring.service.implementations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	

	

		@Override
		public KinderGarten getKinderGById(int id) {
			int countView;
			KinderGarten e = kindergartenRepo.findById(id).get();
			if(e == null) return null;
			
			e.setViews(e.getViews()+1);
		    countView = kindergartenRepo.updateViewCountKinderG(e.getViews()-1,e.getId());
				countView++;
				
			return e ;  

		}


		
		// affiche les 4 les plus visités
				@Override
				public List<String> displayBestKinderGartensByViews() {
					
					List<String>list = new ArrayList<>();
					String s ="";
					List<Integer>listId = new ArrayList<>();
					List<Integer>listViews = new ArrayList<>();
					
					List<KinderGarten> listG = (List<KinderGarten>)kindergartenRepo.findAll();
					
					for(KinderGarten k : listG) {
						listId.add(k.getId());
						listViews.add(k.getViews());
						}
					
					List<Integer> sortedList = new ArrayList<>(listViews);
					
					Collections.sort(sortedList);
					
					for(int i = 0 ; i<3 ; i++) {
						int max = sortedList.get(sortedList.size()-1);
						int ind = listId.get(listViews.indexOf(max));
						s = (i+1)+"--kindergarten: "+kindergartenRepo.findById(ind).get().getName()+"=with"+max+" views";
						list.add(s);
						sortedList.remove(sortedList.size()-1);
						listViews.set(listViews.indexOf(max), -1);
						}
					return list;
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
