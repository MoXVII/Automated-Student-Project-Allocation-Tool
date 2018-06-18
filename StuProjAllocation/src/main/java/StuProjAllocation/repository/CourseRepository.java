package StuProjAllocation.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import StuProjAllocation.domain.Course;







public interface CourseRepository extends CrudRepository<Course, Integer> {
	List<Course> findById(int id);
}