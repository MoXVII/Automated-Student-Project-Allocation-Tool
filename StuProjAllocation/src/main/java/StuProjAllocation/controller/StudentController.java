package StuProjAllocation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import StuProjAllocation.StuProjAllocationApp;
import StuProjAllocation.domain.UniSystem;

import StuProjAllocation.repository.UniSystemRepository;




@Controller
@RequestMapping("/students/")
public class StudentController {
	
	
	@Autowired UniSystemRepository uniSys;
    
    @RequestMapping("/")
    public String index(Model model) { 
    	
    	UniSystem us = uniSys.findByName(StuProjAllocationApp.STORE_NAME).get(0);  //finding our collections store of data in repo   	
    	model.addAttribute("studList",us.getStudList());  //we append the list of students from the backend to the model to display in the view

        return "form/StudentData";
    }
    
	}

