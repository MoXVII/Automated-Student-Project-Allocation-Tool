package StuProjAllocation.repository;

import org.springframework.data.repository.CrudRepository;

import StuProjAllocation.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	public User findById(int id);
	public User findByLogin(String login);

}
