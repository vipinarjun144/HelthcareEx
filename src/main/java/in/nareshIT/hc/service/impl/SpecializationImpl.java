package in.nareshIT.hc.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import in.nareshIT.hc.entity.Specialization;
import in.nareshIT.hc.exception.SpecializationNotFoundException;
import in.nareshIT.hc.repository.SpecializationRepository;
import in.nareshIT.hc.service.ISpecializationService;
import in.nareshIT.hc.util.Util;

@Service
public class SpecializationImpl implements ISpecializationService {

	@Autowired
	private SpecializationRepository repo;

	@Override
	public Long saveSpecialization(Specialization spec) {
		return repo.save(spec).getId();
	}

	@Override
	public List<Specialization> getAllSpecialization() {
		return repo.findAll();
	}

	@Override
	public void removeSpecialization(Long id) {
		//repo.deleteById(id);
		repo.delete(getOneSpecialization(id));
	}

	@Override
	public Specialization getOneSpecialization(Long id) {
		/*
		Optional<Specialization> optional=repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			throw new SpecializationNotFoundException("Oops "+id+" Not Found!!");
		}
		 */
		//TODO:JAVA 8 FEATURE
		return repo.findById(id).orElseThrow(()->new SpecializationNotFoundException("Oops "+id+" Not Found!!"));
	}

	@Override
	public Specialization updateSpecialization(Specialization spec) {
		return repo.save(spec);
	}

	@Override
	public boolean isspecCodeExist(String spec) {
		return repo.getSpecCodeCount(spec)>0;
	}

	@Override
	public boolean isspecCodeExistForEdit(String spec, Integer id) {
		return repo.getSpecCodeCountForEdit(spec, id)>0;
	}

	@Override
	public Map<Long, String> getIdAndSpecName() {
		List<Object[]> list =repo.getIdAndSpecName();
		Map<Long, String> map=Util.convertListToMap(list);
		return map;
	}



}
