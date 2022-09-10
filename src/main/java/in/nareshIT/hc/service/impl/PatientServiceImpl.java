package in.nareshIT.hc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nareshIT.hc.constant.UserRoles;
import in.nareshIT.hc.entity.Patient;
import in.nareshIT.hc.entity.User;
import in.nareshIT.hc.exception.PatientNotFoundException;
import in.nareshIT.hc.repository.PatientRepository;
import in.nareshIT.hc.repository.UserRepository;
import in.nareshIT.hc.service.IPatientService;
import in.nareshIT.hc.util.UserUtil;

@Service
public class PatientServiceImpl implements IPatientService {

	
	@Autowired
	private PatientRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	@Transactional
	public Long savePatient(Patient patient) {
	 Long id= repo.save(patient).getId();
	 if(id!=null) {
		 User user=new User();
		 user.setUserDisplay(patient.getFirstName()+" "+patient.getLastName());
		 user.setUserName(patient.getPatEmail());
		 user.setPassword(UserUtil.genPwd());
		 user.setRole(UserRoles.PATIENT.name());
		 userRepo.save(user);
		 
		 //TODO:email part is pending
		 
	 }
	 return id;
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
