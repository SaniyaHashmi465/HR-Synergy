package com.hrs.hrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hrs.hrs.entity.EventNotification;
import com.hrs.hrs.service.EventService;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/admin/addevent")
    public String showAddEvent() {
        return "admin/addevent";
    }

    @PostMapping("/admin/addevent")
    public String saveEvent(@ModelAttribute EventNotification event,
                            RedirectAttributes redirectAttributes) {

        eventService.saveEvent(event);
        redirectAttributes.addFlashAttribute("success", "Event added successfully.");

        return "redirect:/admin/addevent";
    }

    @GetMapping("/admin/viewevents")
    public String viewEvents(Model model) {

        model.addAttribute("events", eventService.getAllEvents());

        return "admin/viewevents";
    }

    @GetMapping("/admin/deleteevent/{id}")
    public String deleteEvent(@PathVariable Integer id,
                              RedirectAttributes redirectAttributes) {

        eventService.deleteEvent(id);
        redirectAttributes.addFlashAttribute("success", "Event deleted successfully.");

        return "redirect:/admin/viewevents";
    }

    @GetMapping("/admin/editevent/{id}")
    public String editEvent(@PathVariable Integer id,
                            Model model) {

        model.addAttribute("event", eventService.getEventById(id));

        return "admin/editevent";
    }

    @PostMapping("/admin/updateevent")
    public String updateEvent(@ModelAttribute EventNotification event,
                              RedirectAttributes redirectAttributes) {

        eventService.updateEvent(event);
        redirectAttributes.addFlashAttribute("success", "Event updated successfully.");

        return "redirect:/admin/viewevents";
    }

    @GetMapping("/employee/events")
    public String employeeEvents(Model model) {

        model.addAttribute("events", eventService.getAllEvents());

        return "employee/events";
    }
}