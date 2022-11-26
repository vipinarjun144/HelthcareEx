package in.nareshIT.hc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import in.nareshIT.hc.constant.UserRoles;
import in.nareshIT.hc.entity.Doctor;
import in.nareshIT.hc.entity.User;
import in.nareshIT.hc.exception.DoctorNotFoundExceptions;
import in.nareshIT.hc.repository.DoctorRepository;
import in.nareshIT.hc.repository.UserRepository;
import in.nareshIT.hc.service.IDoctorService;
import in.nareshIT.hc.service.IUserService;
import in.nareshIT.hc.util.UserUtil;
import in.nareshIT.hc.util.Util;

@Service
public class DoctorImpl implements IDoctorService {
	
	@Autowired
	private DoctorRepository repo;
	
	@Autowired
	private IUserService userService;
	
	/*
	 * @Autowired private MymailUtil mailSender;
	 */
	
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public Long saveDoctor(Doctor doc) {		
		Long id= repo.save(doc).getId();
				 if(id!=null) {
					 String pwd=UserUtil.genPwd();
					 User user=new User();
					 user.setUserDisplay(doc.getFirstName()+" "+doc.getLastName());
					 user.setUserName(doc.getEmail());
					 user.setPassword(pwd);
					 user.setRole(UserRoles.DOCTOR.name());
					 Long genId=userService.save(user);
					 if(genId!=null) {
						 new Thread(new Runnable() {
							@Override
							public void run() {
								String text="Your Username for doctor is :"+doc.getEmail()+" Password is :"+pwd;
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

	@Override
	public List<Doctor> findDoctorBySpecName(Long specId) {
		List<Object[]> list=repo.findDoctorBySpecName(specId);
		System.out.println("findDoctorBySpecName list>>>>>>>>>>>>>>>>>>>"+list.toString());
		List<Doctor> docList=Util.convertList(list);
		System.out.println("findDoctorBySpecName docList>>>>>>>>>>>>>>>>>>>"+docList);
		return docList;
	}
	

}
