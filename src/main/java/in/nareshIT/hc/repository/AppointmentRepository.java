package in.nareshIT.hc.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.nareshIT.hc.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

@Query("SELECT aptm.date,aptm.noOfSlot,aptm.fees FROM Appointment aptm INNER JOIN aptm.doctor doc WHERE doc.id=:DocId")
List<Object[]> getAppointmentByDoctor(Long DocId);

}
