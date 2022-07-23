package in.nareshIT.hc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.nareshIT.hc.entity.Specialization;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long>  {

	@Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:spec")
	Integer getSpecCodeCount(String spec);


}
