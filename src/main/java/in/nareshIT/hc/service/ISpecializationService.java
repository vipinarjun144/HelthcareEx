package in.nareshIT.hc.service;

import java.util.List;
import java.util.Map;

import in.nareshIT.hc.entity.Specialization;

public interface ISpecializationService {

	public Long saveSpecialization(Specialization spec);
	public List<Specialization> getAllSpecialization();
	public void removeSpecialization(Long id);
	public Specialization getOneSpecialization(Long id);
	public Specialization updateSpecialization(Specialization spec);

	public boolean isspecCodeExist(String spec);

	public boolean isspecCodeExistForEdit(String spec, Integer id);

	public Map<Long,String> getIdAndSpecName();

}
