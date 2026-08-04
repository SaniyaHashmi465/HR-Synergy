package com.hrs.hrs.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrs.hrs.entity.Candidate;
import com.hrs.hrs.entity.Job;
import com.hrs.hrs.entity.JobApplication;
import com.hrs.hrs.service.CandidateService;
import com.hrs.hrs.service.JobApplicationService;
import com.hrs.hrs.service.JobService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ApplicationController {

	@Autowired
	private JobService jobService;

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private JobApplicationService applicationService;

	// Apply Job
	@GetMapping("/candidate/applyjob/{jobid}")
	public String applyJob(@PathVariable Integer jobid,
	                       HttpSession session,
	                       RedirectAttributes redirectAttributes) {

	    String email =
	            (String) session.getAttribute("candidateEmail");

	    if (email == null) {
	        return "redirect:/candidate/login";
	    }

	    Candidate candidate =
	            candidateService.getCandidateByEmail(email);

	    Job job =
	            jobService.getJobById(jobid);

	    if (candidate == null || job == null) {
	        redirectAttributes.addFlashAttribute(
	                "error",
	                "Candidate or job not found.");

	        return "redirect:/candidate/jobs";
	    }

	    JobApplication application =
	            new JobApplication();

	    application.setJobid(job.getJobid());
	    application.setJobprofile(job.getJobprofile());
	    application.setCandidateName(candidate.getName());
	    application.setCandidateEmail(candidate.getEmailaddress());
	    application.setQualification(candidate.getQualification());
	    application.setResume(candidate.getResume());
	    application.setStatus("APPLIED");
	    application.setApplicationDate(LocalDate.now());

	    applicationService.applyJob(application);

	    redirectAttributes.addFlashAttribute(
	            "success",
	            "Job application submitted successfully.");

	    return "redirect:/candidate/applications";
	}
	
	// Candidate Applications
	@GetMapping("/candidate/applications")
	public String myApplications(HttpSession session,
	                             Model model) {

	    String email =
	            (String) session.getAttribute("candidateEmail");

	    if (email == null) {
	        return "redirect:/candidate/login";
	    }

	    model.addAttribute(
	            "applications",
	            applicationService.getCandidateApplications(email));

	    return "candidate/applications";
	}
	
	// Admin View Applications
	@GetMapping("/admin/viewapplications")
	public String viewApplications(Model model) {

		model.addAttribute("applications", applicationService.getAllApplications());

		return "admin/viewapplications";
	}

	@GetMapping("/admin/shortlist/{id}")
	public String shortlistCandidate(@PathVariable Integer id) {

		applicationService.updateStatus(id, "SHORTLISTED");

		return "redirect:/admin/viewapplications";
	}

	@GetMapping("/admin/rejectcandidate/{id}")
	public String rejectCandidate(@PathVariable Integer id) {

		applicationService.updateStatus(id, "REJECTED");

		return "redirect:/admin/viewapplications";
	}

	@GetMapping("/admin/hirecandidate/{id}")
	public String hireCandidate(@PathVariable Integer id) {

		applicationService.updateStatus(id, "HIRED");

		return "redirect:/admin/viewapplications";
	}

}