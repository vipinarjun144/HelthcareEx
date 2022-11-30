package in.nareshIT.hc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshIT.hc.entity.Appointment;
import in.nareshIT.hc.repository.AppointmentRepository;
import in.nareshIT.hc.service.IAppointmentSerice;

@Service
public class AppointmentSericeImpl implements IAppointmentSerice {
	
	@Autowired
	private AppointmentRepository repo;

	@Override
	public Long saveAppointment(Appointment appointment) {
		return repo.save(appointment).getId();
	}

	@Override
	public List<Appointment> getAllAppointments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAppointment(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Appointment getOneAppointment(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAppointment(Appointment Appointment) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Object[]> getAppointmentByDoctor(Long id) {
		
		return repo.getAppointmentByDoctor(id);
	}

}
