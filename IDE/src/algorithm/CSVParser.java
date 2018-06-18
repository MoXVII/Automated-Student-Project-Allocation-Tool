package algorithm;


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
	static List<Preference> studPref = new ArrayList<>();  //list of student preferences parsed from csv
	static List<String> listOfAllStudents = new ArrayList<>();  	
	static List<Project> projectsAvailable = new ArrayList<>(); //list of all projects available parsed from CSV
	static List<String> moduleNamesList= new ArrayList<>(); 
	static List<Student> studentResults = new ArrayList<>(); //list of all students and results parsed from CSV
	
	
	
	
	
	//################# Algorithm Logic variables #################
	public static double fitness;
	public static double weighting;
	public static boolean moduleDone;
	static List<Preference> needReallocation = new ArrayList<>(); //secondary,recursive list for storing students that need reallocations
	static List<Preference> noAllocation = new ArrayList<>();

	static List<Student> allocationMade = new ArrayList<>();
	
	
	
	public static void main(String [] args) {
		
		//################################ PARSING ################################
		
		
		try { 
			BufferedReader br = new BufferedReader(new FileReader("studentResultsFull.csv"));
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

            Student tmpStudent = new Student(tmpName, tmpCourse, tmpModule1, tmpResModule1, tmpModule2, tmpResModule2, tmpModule3, tmpResModule3);
            
            //set preferenceList
            
            // add this new object to the arraylist
            studentResults.add(tmpStudent);

            nextLineModRes = br.readLine();
        }
        br.close();
    }
    catch (FileNotFoundException nf){
        System.out.println("results File Missing");
    }
    catch (IOException noioe){
    	noioe.printStackTrace();
    }
	catch(NumberFormatException nfe) {
			nfe.printStackTrace();
	}		

		
		
	              
		try { 
			BufferedReader br = new BufferedReader(new FileReader("studentPreferencesFull.csv"));
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
            tmpEntry.setFirstPreference(tmpFirstPref);
            tmpEntry.setSecondPreference(tmpSecondPref);
            tmpEntry.setThirdPreference(tmpThirdPref);
		
			// add this new object to the arraylist
            studPref.add(tmpEntry);
            // read next line before looping
            // if end of file reached 
            nextLinePref = br.readLine();
        }
        br.close();
    }
    catch (FileNotFoundException nf){
        System.out.println("preference file missing");
    }
    catch (IOException noioe){
    	noioe.printStackTrace();
    }

		
			
	
		//second bufferedReader to parse the projects CSV file and create the required objects for the algorithm
			try { 
				BufferedReader br = new BufferedReader(new FileReader("projectsAvailable.csv"));
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
	            
	

	            
	            
	            // Here we make an object of our Project class and pass the objects we've read from the csv.
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
	        System.out.println("projects file missing");
	    }
	    catch (IOException noioe){
	    	noioe.printStackTrace();
	    }

		
				
			//################################ Parsing Logic  ################################
		
			// Collect all the students in list
			for(int i= 0;i<studPref.size();i++){
				String studentStore = studPref.get(i).getStudentName();
				listOfAllStudents.add(studentStore);
			}
			
			// Displaying all students that have NOT made preference selections

	////////////////////////// MANUAL TESTING ///////////////////////////
			
	


			
		//these are the methods that run the algorithm step by step. Simply compile all the classes or run in eclipse/IDE to output to console			
		calculateFitnessPreference1(studPref);
		calculateFitnessPreference2(studPref);
		calculateFitnessPreference3(studPref);
		calculateAbsoluteFitness(studPref);
		generateAllocations(studPref);
		generateAllocations(needReallocation);


	//extra debug that shows you how and what allocations have been made		
		System.out.println("Number/List of Students with Allocations Made: " + allocationMade.size());
//		for(Student s : allocationMade) {  uncomment to view all students allocated a project
//			System.out.println(s.getName());
//		}
		System.out.println("##################################");
		System.out.println("Number/List of Students who have No Allocations: " +  noAllocation.size());
//		for(Preference x : noAllocation) {	uncomment to view alll students with NO allocations
//			System.out.println(x.getStudentName());
//		}
		System.out.println("##################################");
		System.out.println("Number of times a student was reallocated a Project (MUTATIONS): " + needReallocation.size());
		System.out.println("##################################");
		

	}

	
	
	//################################ Algorithm Logic  ################################
	
	//this method locates student objects in the preferences with the objects in the studentResults class (student.java)
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
	//this method locates project objects in the preferences with the objects in the project class (project.java)	
	public static Project findProjectByName(String pName) {
		Project projectLookup = null;
		for(int i=0;i<projectsAvailable.size();i++) {
			if(pName.equals(projectsAvailable.get(i).getName())){
				projectLookup = projectsAvailable.get(i);
				break;
			}
		}
		return projectLookup;
	}
	
	
	
	//checking if the student is doing a module that meets the required modulees for a preference. Important for this algorithm
	public static boolean studentDoingModuleRelevantPref(Student stud,Project proj){  //given a student and a project we check if the student did a module that was required by the project
		if(proj.getRequiredModule().getModuleName().equals(stud.getModule1().getModuleName()) || 
				proj.getRequiredModule().getModuleName().equals(stud.getModule2().getModuleName()) ||
				proj.getRequiredModule().getModuleName().equals(stud.getModule3().getModuleName())){
			
			moduleDone = true;
			}else {
				moduleDone = false;
			}
		return moduleDone;
	}
		
 
	
//calculating the fitness for preferences 1,2 (below) ,3 (further below)
	public static void calculateFitnessPreference1(List<Preference> prefList) {
		 for(int i = 0; i< prefList.size();i++) {
			 double[] fitstore = findStudentByName(prefList.get(i).getStudentName()).getFitvals();
			 
			 if(studentDoingModuleRelevantPref(findStudentByName(prefList.get(i).getStudentName()), findProjectByName(prefList.get(i).getFirstPreference().getName())) == true){
				 if(findStudentByName(prefList.get(i).getStudentName()).getModule1().getModuleName().equals(findProjectByName(prefList.get(i).getFirstPreference().getName()).getRequiredModule().getModuleName())) {
						fitness = findStudentByName(prefList.get(i).getStudentName()).getModule1Result() * 0.5;
						fitstore[0] = fitness;
					}else if(findStudentByName(prefList.get(i).getStudentName()).getModule2().getModuleName().equals(findProjectByName(prefList.get(i).getFirstPreference().getName()).getRequiredModule().getModuleName())) {
						fitness = findStudentByName(prefList.get(i).getStudentName()).getModule2Result() * 0.5;
						fitstore[0] = fitness;
					}else if(findStudentByName(prefList.get(i).getStudentName()).getModule3().getModuleName().equals(findProjectByName(prefList.get(i).getFirstPreference().getName()).getRequiredModule().getModuleName())) {
						fitness = findStudentByName(prefList.get(i).getStudentName()).getModule3Result() * 0.5;
						fitstore[0] = fitness;
					} 
			 }else {
				 fitness = 60*0.5;
				 fitstore[0] = fitness;
			 	}
		 	}	
		}
		
		
		public static void calculateFitnessPreference2(List<Preference> prefList) {
			 for(int i = 0; i< prefList.size();i++) {
				 double[] fitstore = findStudentByName(prefList.get(i).getStudentName()).getFitvals();
				 
				 if(studentDoingModuleRelevantPref(findStudentByName(prefList.get(i).getStudentName()), findProjectByName(prefList.get(i).getSecondPreference().getName())) == true){
					 if(findStudentByName(prefList.get(i).getStudentName()).getModule1().getModuleName().equals(findProjectByName(prefList.get(i).getSecondPreference().getName()).getRequiredModule().getModuleName())) {
							fitness = findStudentByName(prefList.get(i).getStudentName()).getModule1Result() * 0.3;
							fitstore[1] = fitness;
						}else if(findStudentByName(prefList.get(i).getStudentName()).getModule2().getModuleName().equals(findProjectByName(prefList.get(i).getSecondPreference().getName()).getRequiredModule().getModuleName())) {
							fitness = findStudentByName(prefList.get(i).getStudentName()).getModule2Result() * 0.3;
							fitstore[1] = fitness;
						}else if(findStudentByName(prefList.get(i).getStudentName()).getModule3().getModuleName().equals(findProjectByName(prefList.get(i).getSecondPreference().getName()).getRequiredModule().getModuleName())) {
							fitness = findStudentByName(prefList.get(i).getStudentName()).getModule3Result() * 0.3;
							fitstore[1] = fitness;
						} 
				 }else {
					 fitness = 60*0.3;
					 fitstore[1] = fitness;
				 	}
			 	}	
			}
		
		public static void calculateFitnessPreference3(List<Preference> prefList) {
			 for(int i = 0; i< prefList.size();i++) {
				 double[] fitstore = findStudentByName(prefList.get(i).getStudentName()).getFitvals();
				 
				 if(studentDoingModuleRelevantPref(findStudentByName(prefList.get(i).getStudentName()), findProjectByName(prefList.get(i).getThirdPreference().getName())) == true){
					 if(findStudentByName(prefList.get(i).getStudentName()).getModule1().getModuleName().equals(findProjectByName(prefList.get(i).getThirdPreference().getName()).getRequiredModule().getModuleName())) {
							fitness = findStudentByName(prefList.get(i).getStudentName()).getModule1Result() * 0.2;
							fitstore[2] = fitness;
						}else if(findStudentByName(prefList.get(i).getStudentName()).getModule2().getModuleName().equals(findProjectByName(prefList.get(i).getThirdPreference().getName()).getRequiredModule().getModuleName())) {
							fitness = findStudentByName(prefList.get(i).getStudentName()).getModule2Result() * 0.2;
							fitstore[2] = fitness;
						}else if(findStudentByName(prefList.get(i).getStudentName()).getModule3().getModuleName().equals(findProjectByName(prefList.get(i).getThirdPreference().getName()).getRequiredModule().getModuleName())) {
							fitness = findStudentByName(prefList.get(i).getStudentName()).getModule3Result() * 0.2;
							fitstore[2] = fitness;
						} 
				 }else {
					 fitness = 60*0.2;
					 fitstore[2] = fitness;
				 	}
			 	}	
			}
		
		
		public static void calculateAbsoluteFitness(List<Preference> inputFile) { 
			DecimalFormat df = new DecimalFormat("#.##");
			
			for(int i=0;i<inputFile.size();i++) {
			double[] adjustedFitstore = findStudentByName(inputFile.get(i).getStudentName()).getAdjustedfitvals();	
			double[] fitstore = findStudentByName(inputFile.get(i).getStudentName()).getFitvals();
			double sum = Arrays.stream(findStudentByName(inputFile.get(i).getStudentName()).getFitvals()).sum();
			
			for(int j = 0; j< fitstore.length;j++) {
				adjustedFitstore[0] = Double.parseDouble(df.format(fitstore[0]/sum));
				adjustedFitstore[1] = Double.parseDouble(df.format(fitstore[1]/sum));
				adjustedFitstore[2] = Double.parseDouble(df.format(fitstore[2]/sum));	
				}
			}
		}
		
		
	

//selection operator		
		public static String roulleteWheelSelection(double[] weights) { 
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
		
		
		
	
		public  static void generateAllocations(List<Preference> pl) {
			
			
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
	
	

	public static List<Preference> getStudPref() {
		return studPref;
	}

	public static List<String> getListOfAllStudents() {
		return listOfAllStudents;
	}

	public static List<Project> getProjectsAvailable() {
		return projectsAvailable;
	}

	public static List<Student> getStudentResults() {
		return studentResults;
	}


}