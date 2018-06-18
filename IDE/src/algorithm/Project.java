package algorithm;






public class Project {


  

    public String name;

	public String description;
	
	
	
	public Module moduleRequired;
	
	public String supervisor;
	
	public String requiredKnowledge;
	public String aimsOfProject;

	public String relevantResources;
    

	// no paramater constructor
    public Project(){}
	
	//parameterised constructors
    public Project(String name) { 
    	this.name = name;
    }
    
   
    public Project(String name, String description,Module moduleRequired,String supervisor, String knowledge, String aims, String resources) { //main constructor for any given project obj in the tool.
    	this.name = name;
    	this.description = description;
    	this.moduleRequired = moduleRequired;
    	this.supervisor = supervisor;
    	this.requiredKnowledge = knowledge;
    	this.aimsOfProject = aims;
    	this.relevantResources = resources;
    	

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
