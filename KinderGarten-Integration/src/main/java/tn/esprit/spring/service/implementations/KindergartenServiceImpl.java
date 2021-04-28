package tn.esprit.spring.service.implementations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Classroom;
import tn.esprit.spring.entity.KinderGarten;
import tn.esprit.spring.repository.IClassroomRepository;
import tn.esprit.spring.repository.IKindergartenRepository;
import tn.esprit.spring.service.interfaces.IKindergartenService;

@Service
public class KindergartenServiceImpl implements IKindergartenService {
	

	
	@Autowired
	IKindergartenRepository kindergartenRepo;
	@Autowired
	IClassroomRepository classeRepo;
	

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

		@Override
		public KinderGarten getkindergartenById(int id) {
			// TODO Auto-generated method stub
			KinderGarten k = kindergartenRepo.findById(id).get();
			if(k==null) return null;
			
			return k;
		}



		
		// affiche les 3 les plus visités
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
						s ="--kindergarten: "+kindergartenRepo.findById(ind).get().getName()+"=with  " +max+" views";
						list.add(s);
						sortedList.remove(sortedList.size()-1);
						listViews.set(listViews.indexOf(max), -1);
						}
					return list;
				}

                @Override
                public List<Classroom> getClassesByKinderg(int id) {
					//List<Classroom> list = new ArrayList<>();
					KinderGarten k = kindergartenRepo.findById(id).get();
					List<Classroom> c=k.getClassrooms();
					return c;
		
				 }
                @Override
				public Double getRevenuePerYearBykinder(int id, String year) {
					// TODO Auto-generated method stub
					return null;
				}


				@Override
				public List<KinderGarten> chercherParZone(Double longi, Double lat,Double rayon) {
					// TODO Auto-generated method stub
					List<KinderGarten> list = new ArrayList<>();
					List<KinderGarten> list2 =(List<KinderGarten>) kindergartenRepo.findAll();
					for(int i=0;i<list2.size();i++)
						
					{
						System.out.println(calculDis(longi,lat,list2.get(i).getLongi(),list2.get(i).getLatitude()));
						if(calculDis(longi,lat,list2.get(i).getLongi(),list2.get(i).getLatitude()) <= rayon)
						{
							list.add(list2.get(i));
						}
					}
					//  long/lat/r
					//coord bd Ariana  long:10.182733  lat: 36.900635 //   long:10.804493  lat2 :36.460875
					
					//test 1 Ariana 10.1852049/36.8989212/1     
					
					//test 2 A GHANA  10.181863/36.806459/1   // long2:10.182039  lat2: 36.806021 
					//
					return list;
				}
				
				
				public Double calculDis(Double longi, Double lat,Double longi1, Double lat1){
					
		            final int R = 6371; // Radious of the earth
					 
					 Double latDistance = toRad(lat1-lat);
					 Double lonDistance = toRad(longi1-longi);
					 Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + 
					 Math.cos(toRad(lat)) * Math.cos(toRad(lat1)) * 
					 Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
					 Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
					 Double distance = R * c;
					 return distance;
				}
				private static Double toRad(Double value) {
					 return value * Math.PI / 180;
					 }

                // changes asp
				@Override
				public KinderGarten updateKindergById(int id,KinderGarten kinder) {
					KinderGarten k = kindergartenRepo.findById(id).get();
					k.setDescription(kinder.getDescription());
					k.setName(kinder.getName());
					k.setPhone(kinder.getPhone());
					k.setDate_creation(kinder.getDate_creation());
					k.setDatefinInscrit(kinder.getDatefinInscrit());
					k.setLongi(kinder.getLongi());
					k.setLatitude(kinder.getLatitude());
					k.setPlace(kinder.getPlace());
					k.setPrice(kinder.getPrice());
					k.setNbr_emp(kinder.getNbr_emp());
					k.setLogo(kinder.getLogo());
					kindergartenRepo.save(kinder);
					return k;
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
