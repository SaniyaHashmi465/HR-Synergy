package com.hrs.hrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrs.hrs.dto.EmployeeDto;
import com.hrs.hrs.entity.Employee;
import com.hrs.hrs.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService empservice;

	@GetMapping("/admin/addemployee")
	public String showAddEmployee() {
		return "admin/addemployee";
	}

	@PostMapping("/admin/addemployee")
	public String saveEmployee(@ModelAttribute EmployeeDto empdto, RedirectAttributes redirectAttributes) {
		try {

			empservice.saveEmployee(empdto);
			redirectAttributes.addFlashAttribute("success", "Employee Registered Successfully.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
		}
		return "redirect:/admin/addemployee";
	}

	@GetMapping("/admin/viewemployees")
	public String viewEmployee(Model model) {
		model.addAttribute("employees", empservice.getAllEmployee());
		return "admin/viewemployees";
	}

	@GetMapping("/admin/deleteemployees/{empid}")
	public String deleteEmployee(@PathVariable Integer empid, RedirectAttributes redirectAttributes) {
		try {

			empservice.deleteEmployee(empid);
			redirectAttributes.addFlashAttribute("success", "Employee deleted Successfully.");

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", " Something went wrong.");
		}

		return "redirect:/admin/viewemployees";
	}

	@GetMapping("/admin/editemployee/{empid}")
	public String editEmployee(@PathVariable Integer empid, Model model) {
		Employee employee = empservice.getEmployeeById(empid);
		model.addAttribute("employee", employee);
		return "admin/editemployee";
	}

	@PostMapping("/admin/updateemployee")
	public String updateEmployee(@ModelAttribute Employee employee, RedirectAttributes rediredAttributes) {

		empservice.updateEmployee(employee);
		rediredAttributes.addFlashAttribute("success", "Employee Updated Successfully.");

		return "redirect:/admin/viewemployees";
	}

	@GetMapping("/admin/searchemployee")
	public String searchEmployee(@RequestParam String keyword, Model model) {

		model.addAttribute("employees", empservice.searchEmployee(keyword));

		model.addAttribute("keyword", keyword);

		return "admin/viewemployees";

	}

	@GetMapping("/employee/profile")
	public String employeeProfile(HttpSession session, Model model) {

		String email = (String) session.getAttribute("employeeEmail");

		if (email == null) {
			return "redirect:/login";
		}

		Employee emp = empservice.getEmployeeByEmail(email);

		if (emp == null) {
			return "redirect:/login";
		}

		model.addAttribute("employee", emp);

		return "employee/profile";
	}

	@GetMapping("/employee/editprofile")
	public String editProfile(HttpSession session, Model model) {

		String email = (String) session.getAttribute("employeeEmail");

		Employee employee = empservice.getEmployeeByEmail(email);

		model.addAttribute("employee", employee);

		return "employee/editprofile";
	}

	@PostMapping("/employee/updateprofile")
	public String updateProfile(@ModelAttribute Employee employee, RedirectAttributes redirectAttributes) {

		empservice.updateEmployee(employee);

		redirectAttributes.addFlashAttribute("success", "Profile updated successfully.");

		return "redirect:/employee/profile";
	}

	@GetMapping("/employee/changepassword")
	public String showChangePassword() {
		return "employee/changepassword";
	}

	@PostMapping("/employee/changepassword")
	public String changePassword(@RequestParam String oldPassword, @RequestParam String newPassword,
			HttpSession session, RedirectAttributes redirectAttributes) {

		String email = (String) session.getAttribute("employeeEmail");

		if (email == null) {
			return "redirect:/login";
		}

		try {
			empservice.changePassword(email, oldPassword, newPassword);
			redirectAttributes.addFlashAttribute("success", "Password changed successfully.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
		}

		return "redirect:/employee/changepassword";
	}

	@GetMapping("/forgotpassword")
	public String showForgotPassword() {
	    return "forgotpassword";
	}

	@PostMapping("/forgotpassword")
	public String resetPassword(@RequestParam String emailaddress,
	                            @RequestParam String newPassword,
	                            RedirectAttributes redirectAttributes) {

	    try {
	        empservice.resetPassword(emailaddress, newPassword);
	        redirectAttributes.addFlashAttribute("success", "Password reset successfully.");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("error", e.getMessage());
	    }

	    return "redirect:/forgotpassword";
	}
}
