package algorithm;




public class Preference { 

	public String studentName;
	

	public Course studentCourse;
	
	

	public Project firstPreference;
	

	public Project secondPreference;
	


	public Project thirdPreference;
	
	
	public Preference() {}
	
	public Preference(String studentName, Course studentCourse, Project firstPreference, Project secondPreference, Project thirdPreference) {
		this.studentName = studentName;
		this.studentCourse = studentCourse;
		this.firstPreference = firstPreference;
		this.secondPreference = secondPreference;
		this.thirdPreference = thirdPreference;
	}
	

	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Course getStudentCourse() {
		return studentCourse;
	}
	public void setStudentCourse(Course studentCourse) {
		this.studentCourse = studentCourse;
	}
	public Project getFirstPreference() {
		return firstPreference;
	}
	public void setFirstPreference(Project firstPreference) {
		this.firstPreference = firstPreference;
	}
	public Project getSecondPreference() {
		return secondPreference;
	}
	public void setSecondPreference(Project secondPreference) {
		this.secondPreference = secondPreference;
	}
	public Project getThirdPreference() {
		return thirdPreference;
	}
	public void setThirdPreference(Project thirdPreference) {
		this.thirdPreference = thirdPreference;
	}





	public String toString() {
		return "Student: " + studentName + "\n" +
			  "Course: " + studentCourse + "\n" +
			  "First Pref: " + firstPreference + "\n" +
			  "Second Pref; " + secondPreference + "\n" +
			  "Third Pref: " + thirdPreference;
				
	}
}
