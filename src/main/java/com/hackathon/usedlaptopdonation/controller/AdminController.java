package com.hackathon.usedlaptopdonation.controller;

import com.hackathon.usedlaptopdonation.model.Laptop;
import com.hackathon.usedlaptopdonation.service.AdminService;
import com.hackathon.usedlaptopdonation.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @Autowired
    private MatchingService matchingService;

    @GetMapping("/home")
    public String showAdminDashboard(Model model) {
        logger.info("Accessing Admin Dashboard...");

        List<Laptop> pendingDonations = adminService.getPendingDonations();
        model.addAttribute("pendingDonations", pendingDonations);

        return "admin-dashboard";
    }

    @PostMapping("/approve/{id}")
    public String approveDonation(@PathVariable("id") Long id) {
        logger.info("Approving laptop with ID: {}", id);
        adminService.approveDonation(id);
        return "redirect:/admin/home";
    }

    @PostMapping("/reject/{id}")
    public String rejectDonation(@PathVariable("id") Long id) {
        logger.info("Rejecting laptop with ID: {}", id);
        adminService.rejectDonation(id);
        return "redirect:/admin/home";
    }

    @PostMapping("/match")
    public String runMatching() {
        logger.info("Admin initiated laptop-request matching process.");
        matchingService.performMatching();
        return "redirect:/admin/home";
    }
}
