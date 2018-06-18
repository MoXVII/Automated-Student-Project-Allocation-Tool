package algorithm;





public class Student {  //Class for storing students and their results


	public String name;
	

	public Course courseStudying;
	

	public Module module1;
	

	public double module1Result;
	

	public Module module2;
	

	public double module2Result;
	

	public Module module3;
	

	public double module3Result;
	

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