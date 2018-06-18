package algorithm;



public class Module {


	public int id;
	
	public String moduleCode;
	
    public String moduleName;

	
		
	//Example of a Module: (COMPSCI, ComputerScience, Taught by Jim Appleseed)
	//So you can have many modules within a course
	//Constructor is used in the courseMaster to display a list consisting of courses
    public Module(String moduleCode, String moduleName){
    	this.moduleCode = moduleCode;
    	this.moduleName = moduleName;
    }
    
    public Module() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}



	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	
	public String toString() {
		return  "Module Code: " + moduleCode + "\n" +
					"Module Name: " + moduleName;
				
}



	
    
}
