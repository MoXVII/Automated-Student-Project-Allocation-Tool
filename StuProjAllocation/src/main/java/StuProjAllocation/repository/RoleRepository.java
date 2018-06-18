package StuProjAllocation.repository;

import org.springframework.data.repository.CrudRepository;

import StuProjAllocation.domain.Role;


public interface RoleRepository extends CrudRepository<Role, Integer> {
	
	public Role findById(int id);

}
