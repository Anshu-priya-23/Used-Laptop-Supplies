package com.hackathon.usedlaptopdonation.controller;

import com.hackathon.usedlaptopdonation.model.Request;
import com.hackathon.usedlaptopdonation.model.User;
import com.hackathon.usedlaptopdonation.service.RequestService;
import com.hackathon.usedlaptopdonation.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/requester")
public class RequesterController {

    private final RequestService requestService;
    private final UserService userService;

    public RequesterController(RequestService requestService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
    }

    // ✅ Dashboard + Add Request form
    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        String email = (String) session.getAttribute("loggedInEmail");

        // ✅ If not logged in, redirect to login
        if (email == null || email.isEmpty()) {
            return "redirect:/login/requester";
        }

        model.addAttribute("request", new Request());
        return "requester-dashboard";
    }

    // ✅ Submit new request → Show Success Page
    @PostMapping("/submit-request")
    public String submitRequest(@ModelAttribute Request request,
                                HttpSession session,
                                Model model) {
        String email = (String) session.getAttribute("loggedInEmail");

        // ✅ Check if session expired
        if (email == null || email.isEmpty()) {
            System.out.println("⚠ Session expired — redirecting to login.");
            return "redirect:/login/requester";
        }

        // ✅ Fetch logged-in requester
        User requester = userService.getUserByEmail(email).orElse(null);
        if (requester == null) {
            System.out.println("⚠ No requester found for email: " + email);
            return "redirect:/login/requester";
        }

        // ✅ Attach user and save
        request.setRequester(requester);
        requestService.saveRequest(request);

        // ✅ Show success page directly (not redirect)
        model.addAttribute("message", "Your request has been submitted successfully!");
        return "requester-success";  // ✅ This will open the success page
    }

    // ✅ View all requests by this requester
    @GetMapping("/view-requests")
    public String viewRequests(Model model, HttpSession session) {
        String email = (String) session.getAttribute("loggedInEmail");

        if (email == null || email.isEmpty()) {
            return "redirect:/login/requester";
        }

        User requester = userService.getUserByEmail(email).orElse(null);
        if (requester == null) {
            return "redirect:/login/requester";
        }

        List<Request> requests = requestService.getRequestsByUser(requester);
        model.addAttribute("requests", requests);

        return "requester-view-requests";
    }
}
