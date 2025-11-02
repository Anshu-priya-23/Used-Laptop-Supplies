package com.hackathon.usedlaptopdonation.controller;

import com.hackathon.usedlaptopdonation.service.ReportService;
import com.hackathon.usedlaptopdonation.model.Laptop;
import com.hackathon.usedlaptopdonation.model.Request;
import com.hackathon.usedlaptopdonation.model.Match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Controller responsible for handling admin reports page.
 *
 * This controller provides endpoints to:
 * - Display the reports dashboard (admin-reports.html)
 *
 * @author Anushka
 */
@Controller
@RequestMapping("/admin/reports")
public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private ReportService reportService;

    /**
     * Displays the admin reports page with donation and request statistics.
     *
     * @param model the model used by Thymeleaf to pass data to the view
     * @return the admin-reports.html view
     */
    @GetMapping
    public String showReports(Model model) {
        logger.info("Loading Admin Reports page...");

        // Fetch data for all reports
        List<Laptop> donations = reportService.getAllDonations();
        List<Request> pendingRequests = reportService.getPendingRequests();
        List<Match> fulfilledDonations = reportService.getFulfilledDonations();

        // Add data to the model
        model.addAttribute("donations", donations);
        model.addAttribute("pendingRequests", pendingRequests);
        model.addAttribute("fulfilledDonations", fulfilledDonations);

        logger.info("Admin Reports page data loaded successfully with {} donations, {} pending requests, and {} fulfilled donations.",
                donations.size(), pendingRequests.size(), fulfilledDonations.size());

        // Return the reports page view
        return "admin-reports";
    }
}
