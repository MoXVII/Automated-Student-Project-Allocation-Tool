package algorithm;


public class Course {			        



    public int id;

	public String courseCode;

	public String courseName;

	
	public Course() {
		
	}


	public Course(String courseCode, String courseName) {		//Constructor for setting up a course.
		this.courseCode = courseCode;
		this.courseName = courseName;
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
