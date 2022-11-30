package in.nareshIT.hc.service;

import java.util.List;

import in.nareshIT.hc.entity.Appointment;

public interface IAppointmentSerice {
	
	public Long saveAppointment(Appointment Appointment);

	public List<Appointment> getAllAppointments();

	public void removeAppointment(Long id);

	public Appointment getOneAppointment(Long id);

	public void updateAppointment(Appointment Appointment);
	
	public List<Object[]> getAppointmentByDoctor(Long id);

}
