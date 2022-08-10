package in.nareshIT.hc.service;

import java.util.List;

import in.nareshIT.hc.entity.Patient;

public interface IPatientService {
	
	public Long savePatient(Patient patient);

	public List<Patient> getAllPatient();

	public void removePatient(Long id);

	public Patient getOnePatient(Long id);

	public void updatePatient(Patient patient);

}
