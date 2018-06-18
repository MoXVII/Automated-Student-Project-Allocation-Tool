package StuProjAllocation.domain;



import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

												
@Entity
public class Module {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	@Column
	private String moduleCode;
	@Column
    private String moduleName;

	
		
	//Example of a Module: (CO2002, Automata)
	//So you can have many modules within a course

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
