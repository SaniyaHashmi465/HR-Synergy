package com.hrs.hrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrs.hrs.entity.Employee;
import com.hrs.hrs.entity.Login;
import com.hrs.hrs.repository.EmployeeRepo;
import com.hrs.hrs.repository.LoginRepo;
import com.hrs.hrs.service.EmployeeService;
import com.hrs.hrs.service.EnquiryService;
import com.hrs.hrs.service.EventService;
import com.hrs.hrs.service.FeedbackService;
import com.hrs.hrs.service.JobApplicationService;
import com.hrs.hrs.service.JobService;
import com.hrs.hrs.service.LeaveService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	@Autowired
	private LoginRepo loginRepo;
	
	@Autowired
	private EmployeeRepo emprepo;
	
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private JobService jobService;

	@Autowired
	private FeedbackService feedbackService;

	@Autowired
	private EventService eventService;

	@Autowired
	private EnquiryService enquiryService;

	@Autowired
	private LeaveService leaveService;

	@Autowired
	private JobApplicationService applicationService;
	
	@GetMapping("/")
	public String showIndex(){
		return "index";
	}
	
	@GetMapping("/login")
	public String showLogin(){
		return "login";
	}
	
	@PostMapping("/login")
	public String validateLogin(@RequestParam String userid,
	                            @RequestParam String passwd,
	                            @RequestParam String usertype,
	                            RedirectAttributes redirectAttributes,
	                            HttpSession session){

	    if(usertype.equals("ADMIN")){

	        Login login = loginRepo.findByUseridAndPasswdAndUsertype(userid, passwd, usertype);

	        if(login != null){
	            session.setAttribute("adminUser", userid);
	            return "redirect:/admin/dashboard";
	        }
	    }
	    else if(usertype.equals("EMPLOYEE")) {

	        Employee emp = emprepo.findByEmailaddressAndPasswd(userid, passwd);

	        if(emp != null) {
	            session.setAttribute("employeeEmail", emp.getEmailaddress());
	            return "redirect:/employee/dashboard";
	        }
	    }

	    redirectAttributes.addFlashAttribute("error","Invalid user ID or Password.");
	    return "redirect:/login";
	}
	
	@GetMapping("/admin/dashboard")
	public String showAdminDashboard(HttpSession session,Model model){

	    if(session.getAttribute("adminUser") == null){
	        return "redirect:/login";
	    }

	    model.addAttribute("totalEmployees", employeeService.countEmployees());
	    model.addAttribute("totalJobs", jobService.countJobs());
	    model.addAttribute("totalFeedback", feedbackService.countFeedback());
	    model.addAttribute("totalEvents", eventService.countEvents());
	    model.addAttribute("totalEnquiries", enquiryService.countEnquiries());
	    model.addAttribute("totalLeaves", leaveService.countLeaves());
	    model.addAttribute("totalApplications", applicationService.countApplications());

	    return "admin/dashboard";
	}

	@GetMapping("/employee/dashboard")
	public String showEmployeeDashboard(HttpSession session){

	    if(session.getAttribute("employeeEmail") == null){
	        return "redirect:/login";
	    }

	    return "employee/dashboard";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/login";
	}
	
	@GetMapping("/joinus")
	public String showJoinUs(){
		return "joinus";
	}
	
	@GetMapping("/about")
	public String showAbout(){
		return "about";
	}
	
}
