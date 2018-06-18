package StuProjAllocation.domain;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Student {  //Class for storing students and their results

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public int id;
	@Column
	public String name;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinTable(name="courseStudying",
		joinColumns = {@JoinColumn(name="student_id", referencedColumnName="id")},
		inverseJoinColumns = {@JoinColumn(name="course_id", referencedColumnName="id")}
	)
	public Course courseStudying;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinTable(name="module1",
		joinColumns = {@JoinColumn(name="student_id", referencedColumnName="id")},
		inverseJoinColumns = {@JoinColumn(name="module_id", referencedColumnName="id")}
	)
	public Module module1;
	
	@Column
	public double module1Result;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinTable(name="module2",
		joinColumns = {@JoinColumn(name="student_id", referencedColumnName="id")},
		inverseJoinColumns = {@JoinColumn(name="module_id", referencedColumnName="id")}
	)
	public Module module2;
	
	@Column	
	public double module2Result;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinTable(name="module3",
		joinColumns = {@JoinColumn(name="student_id", referencedColumnName="id")},
		inverseJoinColumns = {@JoinColumn(name="module_id", referencedColumnName="id")}
	)
	public Module module3;
	
	@Column
	public double module3Result;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="allocatedProj")
	public Project allocatedProj;
	
	public double[] fitvals = new double[3];
	public double[] adjustedfitvals = new double[3];
	
	public Student() {}


	public Student(String name, Course courseStudying, Module module1, double module1Result, Module module2,double module2Result, Module module3, double module3Result) {
		this.name = name;
		this.courseStudying = courseStudying;
		this.module1 = module1;
		this.module1Result = module1Result;
		this.module2 = module2;
		this.module2Result = module2Result;
		this.module3 = module3;
		this.module3Result = module3Result;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Course getCourseStudying() {
		return courseStudying;
	}


	public void setCourseStudying(Course courseStudying) {
		this.courseStudying = courseStudying;
	}


	public Module getModule1() {
		return module1;
	}


	public void setModule1(Module module1) {
		this.module1 = module1;
	}


	public double getModule1Result() {
		return module1Result;
	}


	public void setModule1Result(double module1Result) {
		this.module1Result = module1Result;
	}


	public Module getModule2() {
		return module2;
	}


	public void setModule2(Module module2) {
		this.module2 = module2;
	}


	public double getModule2Result() {
		return module2Result;
	}


	public void setModule2Result(double module2Result) {
		this.module2Result = module2Result;
	}


	public Module getModule3() {
		return module3;
	}


	public void setModule3(Module module3) {
		this.module3 = module3;
	}


	public double getModule3Result() {
		return module3Result;
	}


	public void setModule3Result(double module3Result) {
		this.module3Result = module3Result;
	}



	public double[] getFitvals() {
		return fitvals;
	}


	public void setFitvals(double[] fitvals) {
		this.fitvals = fitvals;
	}


	public double[] getAdjustedfitvals() {
		return adjustedfitvals;
	}


	public void setAdjustedfitvals(double[] adjustedfitvals) {
		this.adjustedfitvals = adjustedfitvals;
	}

	public Project getAllocatedProj() {
		return allocatedProj;
	}

	public void setAllocatedProj(Project allocatedProj) {
		this.allocatedProj = allocatedProj;
	}

	public String toString() {
		return "Student: " + name + "\n" +
			  "Course: " + courseStudying + "\n" +
			  "Module1: " + module1 + "\n" +
			  "Module1Result; " + module1Result + "\n" +
			  "Module2: " + module2	+"\n" +
			  "Module2Result; " + module2Result + "\n" +
			  "Module3: " + module3 + "\n" +
			  "Module3Result; " + module3Result;
}


}