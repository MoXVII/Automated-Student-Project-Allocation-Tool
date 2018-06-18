
package StuProjAllocation.controller;


import static StuProjAllocation.StuProjAllocationApp.ROLE_ADMIN;
import static StuProjAllocation.StuProjAllocationApp.ROLE_LECTURER;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import StuProjAllocation.repository.UserRepository;


@Controller
public class AuthorizationController {
		
	@Autowired UserRepository userRepo;
	
	@RequestMapping(value="/user-login", method=RequestMethod.GET)
	public String loginForm() {
		return "security/login-form";
	}

	@RequestMapping(value="/error-login", method=RequestMethod.GET)
	public String invalidLogin(Model model) {
		model.addAttribute("error", true);
		return "security/login-form";
	}
	
	@RequestMapping(value="/success-login", method=RequestMethod.GET)
	public String successLogin() { 
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  
        StuProjAllocation.domain.User user = userRepo.findByLogin(authUser.getUsername());
        String view;
        switch (user.getRole().getId()) {
        	case ROLE_ADMIN:
        		view = "redirect:/";
        		break;
        	case ROLE_LECTURER: 
        		view = "redirect:/"; 
        		break;
        	default: 
        		view = "redirect:/"; 
        		break;
        }
  
		return view;
	}

	@RequestMapping(value="/user-logout", method=RequestMethod.GET)
	public String logout(Model model) {
		model.addAttribute("logout", true);
		return "security/login-form";
	}
	
	@RequestMapping(value="/access-denied", method=RequestMethod.GET)
	public String error() {
		return "security/error-message";
	}
}