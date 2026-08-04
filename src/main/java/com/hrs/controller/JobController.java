package com.hrs.hrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrs.hrs.entity.Job;
import com.hrs.hrs.service.JobService;

@Controller
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/admin/addjob")
    public String showAddJob() {
        return "admin/addjob";
    }

    @PostMapping("/admin/addjob")
    public String saveJob(@ModelAttribute Job job,
                          RedirectAttributes redirectAttributes) {

        jobService.saveJob(job);

        redirectAttributes.addFlashAttribute(
                "success",
                "Job posted successfully.");

        return "redirect:/admin/addjob";
    }

    @GetMapping("/admin/viewjobs")
    public String viewJobs(Model model) {

        model.addAttribute("jobs", jobService.getAllJobs());

        return "admin/viewjobs";
    }

    @GetMapping("/admin/deletejob/{jobid}")
    public String deleteJob(@PathVariable Integer jobid,
                            RedirectAttributes redirectAttributes) {

        jobService.deleteJob(jobid);

        redirectAttributes.addFlashAttribute(
                "success",
                "Job deleted successfully.");

        return "redirect:/admin/viewjobs";
    }

    @GetMapping("/admin/editjob/{jobid}")
    public String editJob(@PathVariable Integer jobid,
                          Model model) {

        model.addAttribute("job", jobService.getJobById(jobid));

        return "admin/editjob";
    }

    @PostMapping("/admin/updatejob")
    public String updateJob(@ModelAttribute Job job,
                            RedirectAttributes redirectAttributes) {

        jobService.updateJob(job);

        redirectAttributes.addFlashAttribute(
                "success",
                "Job updated successfully.");

        return "redirect:/admin/viewjobs";
    }

	
	  @GetMapping("/jobs") public String publicJobs(Model model) {
	  
	  model.addAttribute("jobs", jobService.getAllJobs());
	  
	  return "/candidate/jobs"; }
	 
}