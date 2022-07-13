package in.nareshIT.hc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nareshIT.hc.entity.Specialization;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long>  {

}
