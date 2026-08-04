package com.hrs.hrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrs.hrs.entity.Feedback;
import com.hrs.hrs.service.FeedbackService;


@Controller
public class FeedbackController {

	@Autowired
	private FeedbackService fservice;
	
	@GetMapping("/employee/feedback")
	public String showFeedbackForm(){
		return "employee/feedback";
	}
	
	@PostMapping("/employee/feedback")
	public String saveFeedback(@ModelAttribute Feedback feedback, RedirectAttributes redirectAttributes){
		fservice.saveFeedback(feedback);
		redirectAttributes.addFlashAttribute("success", "Feedback submitted successfullly.");
		return "redirect:/employee/feedback";
	}
	
	@GetMapping("/admin/viewfeedback")
	public String viewFeedback(Model model){
		
		model.addAttribute("feedbacks", fservice.getAllFeedback() );
		
		return "/admin/viewfeedback";
	}
}
