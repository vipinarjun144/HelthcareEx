package in.nareshIT.hc.service;

import java.util.List;

import in.nareshIT.hc.entity.Doctor;


public interface IDoctorService {
	
	public Long saveDoctor(Doctor spec);
	public List<Doctor> getAllDoctor();
	public void removeDoctor(Long id);
	public Doctor getOneDoctor(Long id);
	public void updateDoctor(Doctor spec);

}
