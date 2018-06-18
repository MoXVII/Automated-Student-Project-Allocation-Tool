package StuProjAllocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import StuProjAllocation.domain.CSVParser;
import StuProjAllocation.domain.Role;
import StuProjAllocation.domain.UniSystem;
import StuProjAllocation.domain.User;
import StuProjAllocation.repository.CourseRepository;
import StuProjAllocation.repository.ModuleRepository;
import StuProjAllocation.repository.PreferenceRepository;
import StuProjAllocation.repository.ProjectRepository;
import StuProjAllocation.repository.StudentRepository;
import StuProjAllocation.repository.SystemRepository;
import StuProjAllocation.repository.UserRepository;


@SpringBootApplication
public class StuProjAllocationApp implements CommandLineRunner { 

	@Autowired 
	private SystemRepository repo;
	
	@Autowired
	private UserRepository userRepo;		
    

	    
	
	public static final int ROLE_ADMIN = 0; 
	public static final int ROLE_LECTURER = 1;  


	public static final String STORE_NAME = "myStudentProjectAllocation";
	
    public static void main(String[] args) {
        SpringApplication.run(StuProjAllocationApp.class, args);
        CSVParser.readProjectsFile("projectsAvailable.csv");
        CSVParser.readStudResultFile("studentResults.csv");       
        CSVParser.readPrefInputFile("student_preferences.csv");
        
    }

    @Override
    public void run(String... strings) throws Exception {
		if (repo.findByName(STORE_NAME).size() == 0) {
			UniSystem system = new UniSystem(STORE_NAME);
			repo.save(system);
		}
		
		BCryptPasswordEncoder pe = new  BCryptPasswordEncoder();

		//ADMIN USER
		User user = new User();
		user.setLogin("Admin");
		user.setPassword(pe.encode("adminpass"));
		Role role = new Role();
		role.setId(ROLE_ADMIN);
		role.setRole("ADMIN");
		user.setRole(role);
		userRepo.save(user);
		
		//LECTURER USER
		user = new User();
		user.setLogin("Lecturer");
		user.setPassword(pe.encode("lecturerpass"));
		role = new Role();
		role.setId(ROLE_LECTURER);
		role.setRole("LECTURER");
		user.setRole(role);
		userRepo.save(user);
		
		
		
    }   
    
}
