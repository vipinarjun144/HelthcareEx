package in.nareshIT.hc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.nareshIT.hc.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	@Query("SELECT id,firstName,lastName FROM Doctor")
	List<Object[]> getIdAndDocName();
	
	@Query("SELECT doct From Doctor doct INNER JOIN doct.specialzation as spc WHERE spc.id=:specId ")
	List<Object[]> findDoctorBySpecName(Long specId);

}
