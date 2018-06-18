package StuProjAllocation.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import StuProjAllocation.domain.Project;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
	List<Project> findById(int id);
    List<Project> findByName(String name);
}