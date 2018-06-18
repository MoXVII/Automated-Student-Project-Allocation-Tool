package StuProjAllocation.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



import StuProjAllocation.domain.Student;







public interface StudentRepository extends CrudRepository<Student, Integer> {
	List<Student> findById(int id);
	List<Student> findByName(String name);
}