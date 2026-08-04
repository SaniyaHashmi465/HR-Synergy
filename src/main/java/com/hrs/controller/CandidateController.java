package com.hrs.hrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrs.hrs.dto.CandidateDto;
import com.hrs.hrs.dto.ChangePasswordDto;
import com.hrs.hrs.entity.Candidate;
import com.hrs.hrs.service.CandidateService;
import com.hrs.hrs.service.JobService;
import jakarta.servlet.http.HttpSession;

@Controller
public class CandidateController {

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private JobService jobService;

	@GetMapping("/candidate/register")
	public String showRegisterForm() {
		return "candidate/register";
	}

	@PostMapping("/candidate/register")
	public String registerCandidate(@ModelAttribute Candidate candidate,
			@RequestParam("resumeFile") MultipartFile resumeFile, RedirectAttributes redirectAttributes) {

		try {
			candidateService.registerCandidate(candidate, resumeFile);

			redirectAttributes.addFlashAttribute("success", "Candidate registered successfully.");

		} catch (Exception e) {

			redirectAttributes.addFlashAttribute("error", e.getMessage());
		}

		return "redirect:/candidate/register";
	}

	@GetMapping("/candidate/login")
	public String showCandidateLogin() {
		return "candidate/login";
	}

	/*
	 * @PostMapping("/candidate/login") public String candidateLogin(String
	 * emailaddress, String passwd, HttpSession session, RedirectAttributes
	 * redirectAttributes) {
	 * 
	 * Candidate candidate = candidateService.loginCandidate(emailaddress, passwd);
	 * 
	 * if (candidate != null) {
	 * 
	 * session.setAttribute("candidateEmail", candidate.getEmailaddress());
	 * 
	 * return "redirect:/candidate/dashboard"; }
	 * 
	 * redirectAttributes.addFlashAttribute( "error", "Invalid email or password.");
	 * 
	 * return "redirect:/candidate/login"; }
	 */
	@PostMapping("/candidate/login")
	public String candidateLogin(@RequestParam String emailaddress, @RequestParam String passwd, HttpSession session,
			RedirectAttributes redirectAttributes) {

		Candidate candidate = candidateService.loginCandidate(emailaddress, passwd);

		if (candidate != null) {

			session.setAttribute("candidateEmail", candidate.getEmailaddress());

			return "redirect:/candidate/dashboard";
		}

		redirectAttributes.addFlashAttribute("error", "Invalid email or password.");

		return "redirect:/candidate/login";
	}

	/*
	 * @GetMapping("/candidate/dashboard") public String candidateDashboard() {
	 * return "candidate/dashboard"; }
	 */
	@GetMapping("/candidate/dashboard")
	public String candidateDashboard(HttpSession session) {

		if (session.getAttribute("candidateEmail") == null) {
			return "redirect:/candidate/login";
		}

		return "candidate/dashboard";
	}

	/*
	 * @GetMapping("/candidate/jobs") public String candidateJobs(Model model){
	 * 
	 * model.addAttribute( "jobs", jobService.getAllJobs());
	 * 
	 * return "redirect:/jobs"; }
	 */
	@GetMapping("/candidate/jobs")
	public String candidateJobs(HttpSession session, Model model) {

		if (session.getAttribute("candidateEmail") == null) {
			return "redirect:/candidate/login";
		}

		model.addAttribute("jobs", jobService.getAllJobs());

		return "candidate/jobs";
	}

	@GetMapping("/candidate/profile")
	public String candidateProfile(HttpSession session, Model model, RedirectAttributes redirectAttributes) {

		String email = (String) session.getAttribute("candidateEmail");

		if (email == null) {
			return "redirect:/candidate/login";
		}

		try {

			CandidateDto candidate = candidateService.getCandidateProfile(email);

			model.addAttribute("candidate", candidate);

			return "candidate/profile";

		} catch (Exception e) {

			redirectAttributes.addFlashAttribute("error", e.getMessage());

			return "redirect:/candidate/dashboard";
		}
	}

	@GetMapping("/candidate/editprofile")
	public String showEditProfile(HttpSession session, Model model, RedirectAttributes redirectAttributes) {

		String email = (String) session.getAttribute("candidateEmail");

		if (email == null) {
			return "redirect:/candidate/login";
		}

		try {

			CandidateDto candidateDto = candidateService.getCandidateProfile(email);

			model.addAttribute("candidateDto", candidateDto);

			return "candidate/editprofile";

		} catch (Exception e) {

			redirectAttributes.addFlashAttribute("error", e.getMessage());

			return "redirect:/candidate/profile";
		}
	}

	@PostMapping("/candidate/updateprofile")
	public String updateCandidateProfile(@ModelAttribute("candidateDto") CandidateDto candidateDto, HttpSession session,
			RedirectAttributes redirectAttributes) {

		String email = (String) session.getAttribute("candidateEmail");

		if (email == null) {
			return "redirect:/candidate/login";
		}

		try {

			candidateService.updateCandidateProfile(candidateDto, email);

			redirectAttributes.addFlashAttribute("success", "Profile updated successfully.");

		} catch (Exception e) {

			redirectAttributes.addFlashAttribute("error", e.getMessage());
		}

		return "redirect:/candidate/profile";
	}

	@GetMapping("/candidate/logout")
	public String candidateLogout(HttpSession session) {

		session.invalidate();

		return "redirect:/candidate/login";
	}

	@GetMapping("/candidate/updateresume")
	public String showUpdateResume(HttpSession session) {

		if (session.getAttribute("candidateEmail") == null) {
			return "redirect:/candidate/login";
		}

		return "candidate/updateresume";
	}

	@PostMapping("/candidate/updateresume")
	public String updateResume(@RequestParam("resumeFile") MultipartFile resumeFile,

			HttpSession session,

			RedirectAttributes redirectAttributes) {

		String email = (String) session.getAttribute("candidateEmail");

		if (email == null) {
			return "redirect:/candidate/login";
		}

		try {

			candidateService.updateResume(email, resumeFile);

			redirectAttributes.addFlashAttribute("success", "Resume updated successfully.");

		} catch (Exception e) {

			redirectAttributes.addFlashAttribute("error", e.getMessage());

		}

		return "redirect:/candidate/profile";
	}

	@GetMapping("/candidate/changepassword")
	public String showChangePassword(HttpSession session, Model model) {

		if (session.getAttribute("candidateEmail") == null) {
			return "redirect:/candidate/login";
		}

		model.addAttribute("passwordDto", new ChangePasswordDto());

		return "candidate/changepassword";
	}

	@PostMapping("/candidate/changepassword")
	public String changePassword(@ModelAttribute("passwordDto") ChangePasswordDto passwordDto, HttpSession session,
			RedirectAttributes redirectAttributes) {

		String email = (String) session.getAttribute("candidateEmail");

		if (email == null) {
			return "redirect:/candidate/login";
		}

		try {

			candidateService.changePassword(email, passwordDto);

			redirectAttributes.addFlashAttribute("success", "Password changed successfully.");

		} catch (Exception e) {

			redirectAttributes.addFlashAttribute("error", e.getMessage());

		}

		return "redirect:/candidate/changepassword";
	}

}