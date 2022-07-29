package in.nareshIT.hc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.nareshIT.hc.entity.Specialization;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long>  {

	@Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:spec")
	Integer getSpecCodeCount(String spec);

	@Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:spec AND id!=:id")
	Integer getSpecCodeCountForEdit(String spec,Integer id);

	@Query("SELECT id,specName FROM Specialization")
	List<Object[]> getIdAndSpecName();

}
