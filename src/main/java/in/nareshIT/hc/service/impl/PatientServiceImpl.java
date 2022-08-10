package in.nareshIT.hc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nareshIT.hc.entity.Patient;
import in.nareshIT.hc.exception.PatientNotFoundException;
import in.nareshIT.hc.repository.PatientRepository;
import in.nareshIT.hc.service.IPatientService;

@Service
public class PatientServiceImpl implements IPatientService {

	
	@Autowired
	private PatientRepository repo;
	
	@Override
	@Transactional
	public Long savePatient(Patient patient) {
	 return repo.save(patient).getId();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Patient> getAllPatient() {
		return repo.findAll();
	}

	@Override
	@Transactional
	public void removePatient(Long id) {
		repo.delete(getOnePatient(id));
		
	}

	@Override
	@Transactional(readOnly = true)
	public Patient getOnePatient(Long id) {
		return repo.findById(id)
				.orElseThrow(()->new PatientNotFoundException("Oops "+id+" Not Found!!"));
	}

	@Override
	public void updatePatient(Patient patient) {
		 repo.save(patient);
	}

}
