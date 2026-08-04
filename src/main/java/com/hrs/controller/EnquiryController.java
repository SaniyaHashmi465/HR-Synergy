package com.hrs.hrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrs.hrs.entity.Enquiry;
import com.hrs.hrs.service.EnquiryService;

@Controller
public class EnquiryController {

    @Autowired
    private EnquiryService enquiryService;

    @GetMapping("/enquiry")
    public String showEnquiryForm() {
        return "enquiry";
    }

    @PostMapping("/enquiry")
    public String saveEnquiry(@ModelAttribute Enquiry enquiry,
                              RedirectAttributes redirectAttributes) {

        enquiryService.saveEnquiry(enquiry);

        redirectAttributes.addFlashAttribute(
                "success",
                "Enquiry submitted successfully.");

        return "redirect:/enquiry";
    }

    @GetMapping("/admin/viewenquiries")
    public String viewEnquiries(Model model) {

        model.addAttribute("enquiries",
                enquiryService.getAllEnquiries());

        return "admin/viewenquiries";
    }

    @GetMapping("/admin/resolveenquiry/{id}")
    public String resolveEnquiry(@PathVariable Integer id,
                                 RedirectAttributes redirectAttributes) {

        enquiryService.markResolved(id);

        redirectAttributes.addFlashAttribute(
                "success",
                "Enquiry marked as resolved.");

        return "redirect:/admin/viewenquiries";
    }

    @GetMapping("/admin/deleteenquiry/{id}")
    public String deleteEnquiry(@PathVariable Integer id,
                                RedirectAttributes redirectAttributes) {

        enquiryService.deleteEnquiry(id);

        redirectAttributes.addFlashAttribute(
                "success",
                "Enquiry deleted successfully.");

        return "redirect:/admin/viewenquiries";
    }
}