package in.nareshIT.hc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshIT.hc.entity.Doctor;
import in.nareshIT.hc.exception.DoctorNotFoundExceptions;
import in.nareshIT.hc.repository.DoctorRepository;
import in.nareshIT.hc.service.IDoctorService;
import in.nareshIT.hc.util.Util;

@Service
public class DoctorImpl implements IDoctorService {
	
	@Autowired
	private DoctorRepository repo;

	@Override
	public Long saveDoctor(Doctor spec) {		
		return repo.save(spec).getId();
	}

	@Override
	public List<Doctor> getAllDoctor() {
				return repo.findAll();
	}

	@Override
	public void removeDoctor(Long id) {
		repo.delete(getOneDoctor(id));

	}

	@Override
	public Doctor getOneDoctor(Long id) {
		return repo.findById(id).orElseThrow(()-> new DoctorNotFoundExceptions(id+" not exite"));
	}

	@Override
	public void updateDoctor(Doctor doc) {
		if(repo.existsById(doc.getId())) {
			repo.save(doc);
		}else {
			throw new DoctorNotFoundExceptions(doc.getId()+" not exite");
		}
	}

	@Override
	public Map<Long, String> getIdAndDocName() {
		List<Object[]> list=repo.getIdAndDocName();
		Map<Long,String> map=Util.convertListToIndex(list);
		return map;
	}

	

}
