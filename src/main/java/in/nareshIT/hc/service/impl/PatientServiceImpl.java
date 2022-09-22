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
import in.nareshIT.hc.service.IUserService;
import in.nareshIT.hc.util.MymailUtil;
import in.nareshIT.hc.util.UserUtil;

@Service
public class PatientServiceImpl implements IPatientService {

	
	@Autowired
	private PatientRepository repo;
	
	@Autowired
	private IUserService userService;
	
	/*
	 * @Autowired private MymailUtil mailSender;
	 */
	
	@Override
	@Transactional
	public Long savePatient(Patient patient) {
	 Long id= repo.save(patient).getId();
	 if(id!=null) {
		 String pwd=UserUtil.genPwd();
		 User user=new User();
		 user.setUserDisplay(patient.getFirstName()+" "+patient.getLastName());
		 user.setUserName(patient.getPatEmail());
		 user.setPassword(pwd);
		 user.setRole(UserRoles.PATIENT.name());
		 
		 Long genId=userService.save(user);
		 if(genId!=null) {
			 new Thread(new Runnable() {
				@Override
				public void run() {
					String text="Your Username is :"+patient.getPatEmail()+" Password is :"+pwd;
					//mailSender.send(patient.getPatEmail(), "Health-Care Credential", text);
					System.out.println(">>>>>>>>>>"+text);
				}
				 
			 }).start();
		 }
		 
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
