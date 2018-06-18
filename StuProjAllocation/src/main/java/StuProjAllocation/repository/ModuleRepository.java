package StuProjAllocation.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import StuProjAllocation.domain.Module;


public interface ModuleRepository extends CrudRepository<Module, Integer> {
	List<Module> findById(int id);
}