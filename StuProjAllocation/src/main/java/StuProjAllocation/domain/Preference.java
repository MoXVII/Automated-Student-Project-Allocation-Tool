package StuProjAllocation.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

@Entity
public class Preference { //Class for storing students and their preferences
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public int id;
	@Column
	public String studentName;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinTable(name="studentCourse",
		joinColumns = {@JoinColumn(name="preference_id", referencedColumnName="id")},
		inverseJoinColumns = {@JoinColumn(name="course_id", referencedColumnName="id")}
	)
	public Course studentCourse;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinTable(name="firstPreference",
		joinColumns = {@JoinColumn(name="preference_id", referencedColumnName="id")},
		inverseJoinColumns = {@JoinColumn(name="project_id", referencedColumnName="id")}
	)
	public Project firstPreference;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinTable(name="secondPreference",
		joinColumns = {@JoinColumn(name="preference_id", referencedColumnName="id")},
		inverseJoinColumns = {@JoinColumn(name="project_id", referencedColumnName="id")}
	)
	public Project secondPreference;
	

	@OneToOne(cascade=CascadeType.ALL)
	@JoinTable(name="thirdPreference",
		joinColumns = {@JoinColumn(name="preference_id", referencedColumnName="id")},
		inverseJoinColumns = {@JoinColumn(name="project_id", referencedColumnName="id")}
	)
	public Project thirdPreference;
	
	
	public Preference() {}
	
	public Preference(String studentName, Course studentCourse, Project firstPreference, Project secondPreference, Project thirdPreference) {
		this.studentName = studentName;
		this.studentCourse = studentCourse;
		this.firstPreference = firstPreference;
		this.secondPreference = secondPreference;
		this.thirdPreference = thirdPreference;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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