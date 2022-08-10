package in.nareshIT.hc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nareshIT.hc.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
