package StuProjAllocation.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import StuProjAllocation.domain.UniSystem;



public interface UniSystemRepository extends CrudRepository<UniSystem, Integer> {
    List<UniSystem> findByName(String name);
    
}