package StuProjAllocation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import StuProjAllocation.StuProjAllocationApp;
import StuProjAllocation.domain.UniSystem;

import StuProjAllocation.repository.UniSystemRepository;




@Controller
@RequestMapping("/preferences/")
public class PreferenceController {
	
	
	@Autowired UniSystemRepository uniSys;
    
    @RequestMapping("/")
    public String index(Model model) { 
    	
    	UniSystem us = uniSys.findByName(StuProjAllocationApp.STORE_NAME).get(0);  //finding our collections store of data in repo   	
    	model.addAttribute("prefList",us.getPrefList());  //we append the list of preferences from the backend to the model to display in the view

        return "form/StudentPreferences";
    }
    
	}

