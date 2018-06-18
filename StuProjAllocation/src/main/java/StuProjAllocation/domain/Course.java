package StuProjAllocation.domain;







import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Course {			        ///Function of this class is to store the courses a student is a part of.
	
	private String user = "";
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)		//Generated auto incrementing 
    private int id;
	@Column
	private String courseCode;
	@Column (length = 3000)			//allocate a larger collumn size to courseName since it contains more content than a regular column
	private String courseName;

	
	public Course() {
		
	}


	public Course(String courseCode, String courseName) {		//Constructor for setting up a course.
		this.courseCode = courseCode;
		this.courseName = courseName;
	}
	



	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCourseCode() {
		return courseCode;
	}


	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public String toString() {
		return "Course Code: " + courseCode + "\n" + 
					"Course Name: " + courseName ;
				
}


}
