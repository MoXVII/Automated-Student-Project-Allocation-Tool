package StuProjAllocation.domain;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashSet;

import java.util.List;


import java.util.Random;
import java.util.Set;




public class CSVParser {					
	

	
	//################# Parsing variables #################
	 static List<Preference> studPref = new ArrayList<>();
	 List<String> listOfAllStudents = new ArrayList<>();
	 List<String> chosenStudents = new ArrayList<>(); //List of all students who have made selections
	 List<String> unchosenStudents = new ArrayList<>(); //List of students who have not made selections
	 static List<Project> projectsAvailable = new ArrayList<>();
	 List<String> moduleNamesList= new ArrayList<>();
	 static List<Student> studentResults = new ArrayList<>();
	
	public  CSVParser() { //constructor for parsing
		readProjectsFile("/webapp/files/projList.csv");	
		readStudResultFile("webapp/files/stuResult.csv");
		readPrefInputFile("/webapp/files/studPref.csv");
	}
	
	public void allocationAlgorithm() {
		//algorithm running method.
		calculateFitnessPreference1(studPref);
		calculateFitnessPreference2(studPref);
		calculateFitnessPreference3(studPref);
		calculateAbsoluteFitness(studPref);
		generateAllocations(studPref);
		generateAllocations(needReallocation);
		setStudentResults(allocationMade); //one extra step for web based UI.
	}
	

	
	
	
	//################# Algorithm Logic variables #################
	public  double fitness;
	public  double weighting;
	public  boolean moduleDone;
	List<Preference> needReallocation = new ArrayList<>(); //secondary,recursive list for storing students that need reallocations
	List<Preference> noAllocation = new ArrayList<>();
	List<Student> allocationMade = new ArrayList<>();
	
	 

	

	 public static Student findStudentByName(String sName) {
			Student studentLookup = null;
			for (int i = 0; i<studentResults.size();i++) {
				if(sName.equals(studentResults.get(i).getName())) {
					studentLookup = studentResults.get(i);
					break;
				}
			}
			return studentLookup;
		}
				
		public static  Project findProjectByName(String pName) {
			Project projectLookup = null;
			for(int i=0;i<projectsAvailable.size();i++) {
				if(pName.equals(projectsAvailable.get(i).getName())){
					projectLookup = projectsAvailable.get(i);
					break;
				}
			}
			return projectLookup;
		}
		
		//################################ PARSING ################################
		
	public static  void readStudResultFile(String fileName) {	
		try { 
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String nextLineModRes = br.readLine();
			while(nextLineModRes != null) {
				String[] completeFile = nextLineModRes.split(",");
				
            // need to store each entry of csv into our class objects

		    String tmpName = completeFile[0];
		    
		    String tmpCourseCode = completeFile[1];
		    String tmpCourseName = completeFile[2];
		    Course tmpCourse =  new Course(tmpCourseCode,tmpCourseName);
		    
		    String tmpModule1Code = completeFile[3];
		    String tmpModule1Name = completeFile[4];
		    Module tmpModule1 = new Module(tmpModule1Code,tmpModule1Name);
            Integer tmpResModule1 = Integer.parseInt(completeFile[5]);
            
            String tmpModule2Code = completeFile[6];
		    String tmpModule2Name = completeFile[7];
            Module tmpModule2 = new Module(tmpModule2Code,tmpModule2Name);
            Integer tmpResModule2 = Integer.parseInt(completeFile[8]);
           
            String tmpModule3Code = completeFile[9];
		    String tmpModule3Name = completeFile[10];
            Module tmpModule3 = new Module(tmpModule3Code,tmpModule3Name);
            Integer tmpResModule3 = Integer.parseInt(completeFile[11]);

            
            // Here we make an object of our Student class and pass the objects we've read from the csv.
            //This is how each student is going to be stored and with a , as delimeter between objects
            Student tmpStudent = new Student(tmpName, tmpCourse, tmpModule1, tmpResModule1, tmpModule2, tmpResModule2, tmpModule3, tmpResModule3);
            
            
            
            // add this new object to the arraylist
            studentResults.add(tmpStudent);
            // read next line before looping
            // if end of file reached 
            nextLineModRes = br.readLine();
        }
        br.close();
    }
    catch (FileNotFoundException nf){
        System.out.println("file not found");
    }
    catch (IOException noioe){
    	noioe.printStackTrace();
    }
	catch(NumberFormatException nfe) {
			nfe.printStackTrace();
	}		

	}	
	
	
	   public static  void readPrefInputFile(String fileName)   {		   
		try { 
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String nextLinePref = br.readLine();
			while(nextLinePref != null) {
				String[] completeFile = nextLinePref.split(",");
		
            // need to store each entry of csv into our class objects
		    String tmpName = completeFile[0];
		    String tmpCourseCode = completeFile[1];
		    String tmpCourseName = completeFile[2];
            Course tmpCourse = new Course(tmpCourseCode, tmpCourseName);
            Project tmpFirstPref = new Project(completeFile[3]);
            Project tmpSecondPref = new Project(completeFile[4]);
            Project tmpThirdPref = new Project(completeFile[5]);
            
            
            
            // Here we make an object of our Preference class and pass the objects we've read from the csv.
            //This is how each student is going to be stored and with a , as delimeter between objects
            Preference tmpEntry = new Preference(tmpName, tmpCourse, tmpFirstPref,tmpSecondPref,tmpThirdPref);
            tmpEntry.setFirstPreference(findProjectByName(tmpFirstPref.getName()));
            tmpEntry.setSecondPreference(findProjectByName(tmpSecondPref.getName()));
            tmpEntry.setThirdPreference(findProjectByName(tmpThirdPref.getName()));
            
            
            
            // add this new object to the arraylist
            studPref.add(tmpEntry);
            // read next line before looping
            // if end of file reached 
            nextLinePref = br.readLine();
        }
        br.close();
    }
    catch (FileNotFoundException nf){
        System.out.println("file not found");
    }
    catch (IOException noioe){
    	noioe.printStackTrace();
    }
}
		
			
	
		public static  void readProjectsFile(String fileName) { 
			try { 
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				String nextLineProj = br.readLine();
				while(nextLineProj != null) {
					String[] completeFile = nextLineProj.split(",");
					
	            // need to store each entry of csv into our class objects
	
			    String tmpProjName = completeFile[0];
			    String tmpProjDesc =  completeFile[1];
			    String tmpModuleCode = completeFile[2];
			    String tmpModuleName = completeFile[3];
			    Module tmpRequiredModule = new Module(tmpModuleCode, tmpModuleName);
	            String tmpSupervisor = completeFile[4];
	            String tmpRequiredProjKnowledge = completeFile[5];
	            String tmpProjectAims = completeFile[6];
	            String tmpProjectResources = completeFile[7];
	            
	

	            
	            
	            // Here we make an object of our Projects class and pass the objects we've read from the csv.
	            //This is how each student is going to be stored and with a , as delimeter between objects
	            Project tmpProject = new Project(tmpProjName, tmpProjDesc, tmpRequiredModule, tmpSupervisor, tmpRequiredProjKnowledge, tmpProjectAims, tmpProjectResources);
	            // add this new object to the arraylist
	            projectsAvailable.add(tmpProject);
	            // read next line before looping
	            // if end of file reached 
	            nextLineProj = br.readLine();
	        }
	        br.close();
	    }
	    catch (FileNotFoundException nf){
	        System.out.println("file not found");
	    }
	    catch (IOException noioe){
	    	noioe.printStackTrace();
	    }

	}		
				
			//################################ Parsing Logic  ################################
		
			// Collect all the students in list
		public void returnListofStudents(List<Preference> prefList){
			for(int i= 0;i<prefList.size();i++){
				String studentStore = studPref.get(i).getStudentName();
				listOfAllStudents.add(studentStore);
			}
		}
		
		public void findStudentsMadeSelectionsOrNot (List<Preference> prefList){
			// Displaying all students that have NOT made preference selections
			for(int i=0;i<prefList.size();i++) {
				if(studPref.get(i).getFirstPreference().getName().isEmpty() ||
					studPref.get(i).getSecondPreference().getName().isEmpty()|| 
					studPref.get(i).getThirdPreference().getName().isEmpty()) {
					String studentNotSelected = studPref.get(i).getStudentName();
					unchosenStudents.add(studentNotSelected);
				}else { //or have made selections
					String studentSelected = studPref.get(i).getStudentName();
					chosenStudents.add(studentSelected);
				}
			}
		}
	
	

		
		
		
//################################ Algorithm Logic  ################################
	
	
	
	
	public  boolean studentDoingModuleRelevantPref(Student stud,Project proj){  //given a student and a project we check if the student did a module that was required by the project
		if(proj.getRequiredModule().getModuleName().equals(stud.getModule1().getModuleName()) || 
				proj.getRequiredModule().getModuleName().equals(stud.getModule2().getModuleName()) ||
				proj.getRequiredModule().getModuleName().equals(stud.getModule3().getModuleName())){
			
			moduleDone = true;
			}else {
				moduleDone = false;
			}
		return moduleDone;
	}
		
 
	

	public  void calculateFitnessPreference1(List<Preference> prefList){
		 for(int i = 0; i< prefList.size();i++) {
			 double[] fitstore = findStudentByName(prefList.get(i).getStudentName()).getFitvals();
			 
			 if(studentDoingModuleRelevantPref(findStudentByName(prefList.get(i).getStudentName()), (prefList.get(i).getFirstPreference())) == true){
				 if(findStudentByName(prefList.get(i).getStudentName()).getModule1().getModuleName().equals(prefList.get(i).getFirstPreference().getRequiredModule().getModuleName())) {
						fitness = findStudentByName(prefList.get(i).getStudentName()).getModule1Result() * 0.5;
						fitstore[0] = fitness;
					}else if(findStudentByName(prefList.get(i).getStudentName()).getModule2().getModuleName().equals(prefList.get(i).getFirstPreference().getRequiredModule().getModuleName())) {
						fitness = findStudentByName(prefList.get(i).getStudentName()).getModule2Result() * 0.5;
						fitstore[0] = fitness;
					}else if(findStudentByName(prefList.get(i).getStudentName()).getModule3().getModuleName().equals(prefList.get(i).getFirstPreference().getRequiredModule().getModuleName())) {
						fitness = findStudentByName(prefList.get(i).getStudentName()).getModule3Result() * 0.5;
						fitstore[0] = fitness;
					} 
			 }else {
				 fitness = 60*0.5;
				 fitstore[0] = fitness;
			 	}
		 	}	
		}
		
		
		public  void calculateFitnessPreference2(List<Preference> prefList) {
			 for(int i = 0; i< prefList.size();i++) {
				 double[] fitstore = findStudentByName(prefList.get(i).getStudentName()).getFitvals();
				 
				 if(studentDoingModuleRelevantPref(findStudentByName(prefList.get(i).getStudentName()), (prefList.get(i).getSecondPreference())) == true){
					 if(findStudentByName(prefList.get(i).getStudentName()).getModule1().getModuleName().equals(prefList.get(i).getSecondPreference().getRequiredModule().getModuleName())) {
							fitness = findStudentByName(prefList.get(i).getStudentName()).getModule1Result() * 0.3;
							fitstore[1] = fitness;
						}else if(findStudentByName(prefList.get(i).getStudentName()).getModule2().getModuleName().equals(prefList.get(i).getSecondPreference().getRequiredModule().getModuleName())) {
							fitness = findStudentByName(prefList.get(i).getStudentName()).getModule2Result() * 0.3;
							fitstore[1] = fitness;
						}else if(findStudentByName(prefList.get(i).getStudentName()).getModule3().getModuleName().equals(prefList.get(i).getSecondPreference().getRequiredModule().getModuleName())) {
							fitness = findStudentByName(prefList.get(i).getStudentName()).getModule3Result() * 0.3;
							fitstore[1] = fitness;
						} 
				 }else {
					 fitness = 60*0.3;
					 fitstore[1] = fitness;
				 	}
			 	}	
			}
		
		public  void calculateFitnessPreference3(List<Preference> prefList) {
			 for(int i = 0; i< prefList.size();i++) {
				 double[] fitstore = findStudentByName(prefList.get(i).getStudentName()).getFitvals();
				 
				 if(studentDoingModuleRelevantPref(findStudentByName(prefList.get(i).getStudentName()), (prefList.get(i).getThirdPreference())) == true){
					 if(findStudentByName(prefList.get(i).getStudentName()).getModule1().getModuleName().equals(prefList.get(i).getThirdPreference().getRequiredModule().getModuleName())) {
							fitness = findStudentByName(prefList.get(i).getStudentName()).getModule1Result() * 0.2;
							fitstore[2] = fitness;
						}else if(findStudentByName(prefList.get(i).getStudentName()).getModule2().getModuleName().equals(prefList.get(i).getThirdPreference().getRequiredModule().getModuleName())){
							fitness = findStudentByName(prefList.get(i).getStudentName()).getModule2Result() * 0.2;
							fitstore[2] = fitness;
						}else if(findStudentByName(prefList.get(i).getStudentName()).getModule3().getModuleName().equals(prefList.get(i).getThirdPreference().getRequiredModule().getModuleName())){
							fitness = findStudentByName(prefList.get(i).getStudentName()).getModule3Result() * 0.2;
							fitstore[2] = fitness;
						} 
				 }else {
					 fitness = 60*0.2;
					 fitstore[2] = fitness;
				 	}
			 	}	
			}
		
		
		public  void calculateAbsoluteFitness(List<Preference> inputFile) { 
			DecimalFormat df = new DecimalFormat("#.##");
			
			for(int i=0;i<inputFile.size();i++) {
			double[] adjustedFitstore = findStudentByName(studPref.get(i).getStudentName()).getAdjustedfitvals();	
			double[] fitstore = findStudentByName(studPref.get(i).getStudentName()).getFitvals();
			double sum = Arrays.stream(findStudentByName(inputFile.get(i).getStudentName()).getFitvals()).sum();
			
			for(int j = 0; j< fitstore.length;j++) {
				adjustedFitstore[0] = Double.parseDouble(df.format(fitstore[0]/sum));
				adjustedFitstore[1] = Double.parseDouble(df.format(fitstore[1]/sum));
				adjustedFitstore[2] = Double.parseDouble(df.format(fitstore[2]/sum));	
				}
			}
		}
		
		
	

		
		public  String roulleteWheelSelection(double[] weights) { 
			DecimalFormat df = new DecimalFormat("#.##");
			Random r = new Random();
			double randomNumber = Double.parseDouble(df.format(r.nextDouble()));
			System.out.println(randomNumber);
			double bounds[][] = {{0,0}, {0,0}, {0,0}};
			
			bounds[0] = new double[] {0, weights[0]};			
			bounds[1] = new double[] {weights[0], weights[0]+weights[1]};
			bounds[2] = new double[] {weights[0]+weights[1], weights[0]+weights[1]+weights[2]};
			String output = "";
			for(int j = 0; j < weights.length; j++) {
				if(randomNumber >= bounds[0][0] && randomNumber <= bounds[0][1]) { 
					output = "Preference 1";
				} else if(randomNumber > bounds[1][0] && randomNumber <= bounds[1][1]) { 
					output = "Preference 2";
				} else if(randomNumber > bounds[2][0] && randomNumber <= bounds[2][1]) { 
					output = "Preference 3";

				}
			}		
			System.out.println("Allocated Project: " + output);			
			return output;
			
		}
		
		
		public  void generateAllocations(List<Preference> pl) {
			
			for(int i=0;i<pl.size();i++) {
				 String receivedAllocation = roulleteWheelSelection(findStudentByName(pl.get(i).getStudentName()).getAdjustedfitvals());		 
				 
					 
					 if(receivedAllocation.equals("Preference 1") && projectsAvailable.contains(findProjectByName(pl.get(i).getFirstPreference().getName()))==true) {  				
							 findStudentByName(pl.get(i).getStudentName()).setAllocatedProj(pl.get(i).getFirstPreference()); //allocating stud to first preference.
							 projectsAvailable.remove(findProjectByName(pl.get(i).getFirstPreference().getName()));  //removing the project from the list of availbe projects
							 System.out.println("Student Name: " + findStudentByName(pl.get(i).getStudentName()).getName());
							 System.out.println(findStudentByName(pl.get(i).getStudentName()).allocatedProj.getName());
							 allocationMade.add(findStudentByName(pl.get(i).getStudentName()));
							 System.out.println("====================================================================="); 
					 }else if(projectsAvailable.contains(findProjectByName(pl.get(i).getFirstPreference().getName())) == false && receivedAllocation.equals("Preference 1")){ //preference 1 taken
						 
						 if (projectsAvailable.contains(findProjectByName(pl.get(i).getSecondPreference().getName())) == true || 
								 projectsAvailable.contains(findProjectByName(pl.get(i).getThirdPreference().getName())) == true ){ //checking if maybe pref 2 or 3 are available
							 System.out.println("STUDENT: " + findStudentByName(pl.get(i).getStudentName()).getName() + " FIRST PREF DUPLICATE.WILL REALLOCATE");
							 needReallocation.add(pl.get(i));
							 System.out.println("=====================================================================");
						 }else { //preferences 1,2,3 are all taken 	
								 noAllocation.add(pl.get(i));			
								 System.out.println("Student Name: " + findStudentByName(pl.get(i).getStudentName()).getName() +  " HAS NO REMAINING PREFERENCES, THEY NEED TO RE-SELECT.");
								 System.out.println("=====================================================================");
							 }
						 
						 
						 
						 
					 }else if(receivedAllocation.equals("Preference 2") && projectsAvailable.contains(findProjectByName(pl.get(i).getSecondPreference().getName()))==true) {  					
							 findStudentByName(pl.get(i).getStudentName()).setAllocatedProj(pl.get(i).getSecondPreference()); 
							 projectsAvailable.remove(findProjectByName(pl.get(i).getSecondPreference().getName()));
							 System.out.println("Student Name: " + findStudentByName(pl.get(i).getStudentName()).getName());
							 System.out.println(findStudentByName(pl.get(i).getStudentName()).allocatedProj.getName());
							 allocationMade.add(findStudentByName(pl.get(i).getStudentName()));
							 System.out.println("====================================================================="); 
					 }else if(projectsAvailable.contains(findProjectByName(pl.get(i).getSecondPreference().getName())) == false && receivedAllocation.equals("Preference 2")){
						
						 if (projectsAvailable.contains(findProjectByName(pl.get(i).getFirstPreference().getName())) == true || 
								 projectsAvailable.contains(findProjectByName(pl.get(i).getThirdPreference().getName())) == true){
							 System.out.println("STUDENT: " + findStudentByName(pl.get(i).getStudentName()).getName() + " SECOND PREF DUPLICATE.WILL REALLOCATE");
							 needReallocation.add(pl.get(i));
							 System.out.println("=====================================================================");
						 }else { 
								 noAllocation.add(pl.get(i));			
								 System.out.println("Student Name: " + findStudentByName(pl.get(i).getStudentName()).getName() +  " HAS NO REMAINING PREFERENCES, THEY NEED TO RE-SELECT.");
								 System.out.println("=====================================================================");
							 }

							 
				 }else if(receivedAllocation.equals("Preference 3") && projectsAvailable.contains(findProjectByName(pl.get(i).getThirdPreference().getName())) == true){  						
							 findStudentByName(pl.get(i).getStudentName()).setAllocatedProj(pl.get(i).getThirdPreference()); //allocating stud to first preference.
							 projectsAvailable.remove(findProjectByName(pl.get(i).getThirdPreference().getName()));
							 System.out.println("Student Name: " + findStudentByName(pl.get(i).getStudentName()).getName());
							 System.out.println(findStudentByName(pl.get(i).getStudentName()).allocatedProj.getName());
							 allocationMade.add(findStudentByName(pl.get(i).getStudentName()));
							 System.out.println("====================================================================="); 
					 }else if(projectsAvailable.contains(findProjectByName(pl.get(i).getThirdPreference().getName())) == false && receivedAllocation.equals("Preference 3")){					
						
						 if (projectsAvailable.contains(findProjectByName(pl.get(i).getFirstPreference().getName())) == true ||
								 projectsAvailable.contains(findProjectByName(pl.get(i).getSecondPreference().getName())) == true  ){
							 System.out.println("STUDENT: " + findStudentByName(pl.get(i).getStudentName()).getName() + " THIRD PREF DUPLICATE.WILL REALLOCATE");
							 needReallocation.add(pl.get(i));
							 System.out.println("=====================================================================");
						 }else {
								 noAllocation.add(pl.get(i));			
								 System.out.println("Student Name: " + findStudentByName(pl.get(i).getStudentName()).getName() +  " HAS NO REMAINING PREFERENCES, THEY NEED TO RE-SELECT.");
								 System.out.println("=====================================================================");
							 }

					 }
							 
							
				
				
				
				
				
				
				
				}
		 }
	

		
	public List<String> getModuleList(){
		Set<String> ms = new HashSet<>();
		for(int i = 0; i < projectsAvailable.size(); i++){
		        String code = projectsAvailable.get(i).getRequiredModule().getModuleCode();
		        if(code.charAt(0) == 'C' ||  code.equals("None")){
		                ms.add(code);			                
		        }    
		}
		moduleNamesList.addAll(ms);
		return moduleNamesList;
	}
	
	
	public List<String> getChosenStudentList() {
		return chosenStudents;
	}
	
	public List<String> getUnchosenStudentList(){
		return unchosenStudents;
	}

	public  List<Preference> getStudPref() {
		return studPref;
	}

	public static void setStudPref(List<Preference> studPref) {
		CSVParser.studPref = studPref;
	}

	public  List<String> getListOfAllStudents() {
		return listOfAllStudents;
	}

	public  List<Project> getProjectsAvailable() {
		return projectsAvailable;
	}

	public List<Student> getStudentResults() {
		return studentResults;
	}

	public List<Preference> getNeedReallocation() {
		return needReallocation;
	}

	public void setNeedReallocation(List<Preference> needReallocation) {
		this.needReallocation = needReallocation;
	}

	public List<Preference> getNoAllocation() {
		return noAllocation;
	}

	public void setNoAllocation(List<Preference> noAllocation) {
		this.noAllocation = noAllocation;
	}

	public List<Student> getAllocationMade() {
		return allocationMade;
	}

	public void setAllocationMade(List<Student> allocationMade) {
		this.allocationMade = allocationMade;
	}

	public static void setStudentResults(List<Student> studentResults) {
		CSVParser.studentResults = studentResults;
	}



}