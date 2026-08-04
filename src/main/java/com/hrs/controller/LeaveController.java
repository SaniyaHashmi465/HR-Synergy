package com.hrs.hrs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hrs.hrs.entity.LeaveRequest;
import com.hrs.hrs.service.LeaveService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    // Employee Leave Form
    @GetMapping("/employee/applyleave")
    public String showLeaveForm() {
        return "employee/applyleave";
    }

    // Save Leave Request
    @PostMapping("/employee/applyleave")
    public String applyLeave(@ModelAttribute LeaveRequest leaveRequest,
                             RedirectAttributes redirectAttributes) {

        leaveService.applyLeave(leaveRequest);

        redirectAttributes.addFlashAttribute(
                "success",
                "Leave request submitted successfully.");

        return "redirect:/employee/applyleave";
    }

    // Employee Leave History
    @GetMapping("/employee/myleaves")
    public String myLeaves(HttpSession session,
                           Model model) {

        String email =
                (String) session.getAttribute("employeeEmail");

        List<LeaveRequest> leaves =
                leaveService.getEmployeeLeaves(email);

        model.addAttribute("leaves", leaves);

        return "employee/myleaves";
    }

    // Admin View All Leaves
    @GetMapping("/admin/viewleaves")
    public String viewLeaves(Model model) {

        model.addAttribute("leaves", leaveService.getAllLeaves());

        model.addAttribute("totalLeaves", leaveService.countAllLeaves());
        model.addAttribute("pendingLeaves", leaveService.countByStatus("PENDING"));
        model.addAttribute("approvedLeaves", leaveService.countByStatus("APPROVED"));
        model.addAttribute("rejectedLeaves", leaveService.countByStatus("REJECTED"));

        return "admin/viewleaves";
    }

    // Approve Leave
    @GetMapping("/admin/approveleave/{leaveid}")
    public String approveLeave(@PathVariable Integer leaveid,
                               RedirectAttributes redirectAttributes) {

        leaveService.approveLeave(leaveid);

        redirectAttributes.addFlashAttribute(
                "success",
                "Leave Approved Successfully.");

        return "redirect:/admin/viewleaves";
    }

    // Reject Leave
    @GetMapping("/admin/rejectleave/{leaveid}")
    public String rejectLeave(@PathVariable Integer leaveid,
                              RedirectAttributes redirectAttributes) {

        leaveService.rejectLeave(leaveid);

        redirectAttributes.addFlashAttribute(
                "success",
                "Leave Rejected Successfully.");

        return "redirect:/admin/viewleaves";
    }
    
    
}