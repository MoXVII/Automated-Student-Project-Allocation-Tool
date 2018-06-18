package StuProjAllocation.domain;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
	@Column
    private String name;
	@Column
	private String description;
	
	@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
	private Module moduleRequired;
	@Column
	private String supervisor;
	@Column
	private String requiredKnowledge;
	@Column
	private String aimsOfProject;
	@Column
	private String relevantResources;
    

	
    public Project(){}
    
    public Project(String name) { //constructor for handling preferences.csv
    	this.name = name;
    }
    
    //Constructor for making a new project. 
    //
    public Project(String name, String description,Module moduleRequired,String supervisor, String knowledge, String aims, String resources) { //main constructor for any given project obj in the tool.
    	this.name = name;
    	this.description = description;
    	this.moduleRequired = moduleRequired;
    	this.supervisor = supervisor;
    	this.requiredKnowledge = knowledge;
    	this.aimsOfProject = aims;
    	this.relevantResources = resources;
    	

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Module getRequiredModule() {
		return moduleRequired;
	}

	public void setRequiredModule(Module module) {
		this.moduleRequired = module;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getRequiredKnowledge() {
		return requiredKnowledge;
	}

	public void setRequiredKnowledge(String requiredKnowledge) {
		this.requiredKnowledge = requiredKnowledge;
	}

	public String getAimsOfProject() {
		return aimsOfProject;
	}

	public void setAimsOfProject(String aimsOfProject) {
		this.aimsOfProject = aimsOfProject;
	}

	public String getRelevantResources() {
		return relevantResources;
	}

	public void setRelevantResources(String relevantResources) {
		this.relevantResources = relevantResources;
	}

	@Override
	public String toString() {
		return "Project:" + name + "\n" +
				"Description: " + description + "\n" +
				"ModuleRequired: " + moduleRequired + "\n" +
				"Supervisor: " + supervisor + "\n" +
				"Required Knowledge: " + requiredKnowledge + "\n" +
				"Project Aims: "+ aimsOfProject  + "\n" +
				"relevantResources: " + relevantResources;
	}

	

	
}
