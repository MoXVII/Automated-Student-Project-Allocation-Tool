package StuProjAllocation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import javax.servlet.http.HttpServletRequest;

import StuProjAllocation.StuProjAllocationApp;
import StuProjAllocation.domain.CSVParser;
import StuProjAllocation.domain.Preference;
import StuProjAllocation.domain.Project;
import StuProjAllocation.domain.Student;
import StuProjAllocation.domain.UniSystem;
import StuProjAllocation.repository.CourseRepository;
import StuProjAllocation.repository.ModuleRepository;
import StuProjAllocation.repository.PreferenceRepository;
import StuProjAllocation.repository.ProjectRepository;
import StuProjAllocation.repository.StudentRepository;
import StuProjAllocation.repository.UniSystemRepository;




@Controller
@RequestMapping("/")
public class IndexController {
	
    @Autowired
    private HttpServletRequest request;
	
    @Autowired UniSystemRepository uniRepo;
    @Autowired ProjectRepository projectRepo;
    @Autowired StudentRepository studRepo;
    @Autowired PreferenceRepository prefRepo;
    @Autowired ModuleRepository modRepo;
    @Autowired CourseRepository courseRepo;
    
    @RequestMapping("/")
    public String index() {
        return "index";
    }
    
	@RequestMapping(value="/accessDenied", method=RequestMethod.GET)
	public String error() {
		return "security/error-message";
	}
	
	@RequestMapping(value="/parsingInput", method=RequestMethod.POST)
	public String parseFiles(
					@RequestParam(value="prefFile", required=true) MultipartFile prefFile,
					@RequestParam(value="studResult", required=true) MultipartFile studResult,
					@RequestParam(value="projList", required=true) MultipartFile projList){
	
		try {
		if(prefFile !=null && !prefFile.isEmpty()){
			String dir = "/files/";
			String realPath = request.getServletContext().getRealPath(dir);
			if(! new File(realPath).exists()){
				new File(realPath).mkdir();
			}
			File dest = new File(realPath+"prefFile.csv");
			prefFile.transferTo(dest);
		}

		if(studResult !=null && !studResult.isEmpty()){
			String dir = "/files/";
			String realPath = request.getServletContext().getRealPath(dir);
			if(! new File(realPath).exists()){
				new File(realPath).mkdir();
			}
			File dest = new File(realPath+"studResult.csv");
			studResult.transferTo(dest);
		}
		
		if(projList !=null && !projList.isEmpty()){
			String dir = "/files/";
			String realPath = request.getServletContext().getRealPath(dir);
			if(! new File(realPath).exists()){
				new File(realPath).mkdir();
			}
			File dest = new File(realPath+"projList.csv");
			projList.transferTo(dest);
		}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		UniSystem uniSys = uniRepo.findByName(StuProjAllocationApp.STORE_NAME).get(0);
		
		CSVParser csvp = new CSVParser();  //parsing the received files
		csvp.allocationAlgorithm();		//running the algorithm
		
		
		
		for(Student s:csvp.getStudentResults()) { //storing studentData
			uniSys.getStudList().add(s);
			studRepo.save(s);		
		}
		
		
		for(Preference p: csvp.getStudPref()) { //storing preferenceData 
			if(csvp.getNeedReallocation().contains(p) != true) {
				uniSys.getPrefList().add(p);
				prefRepo.save(p);
			}else {
				continue;
			}
			
		}
			
		for(Project x:csvp.getProjectsAvailable()) {
			uniSys.getProjList().add(x);
			projectRepo.save(x);
		}
		

		
	
		return "redirect:/projects/";
		
	}
	
	
	
	
	

}
