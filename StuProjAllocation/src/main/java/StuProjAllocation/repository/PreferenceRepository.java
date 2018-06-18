package StuProjAllocation.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



import StuProjAllocation.domain.Preference;







public interface PreferenceRepository extends CrudRepository<Preference, Integer> {
	List<Preference> findById(int id);
}