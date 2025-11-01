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

        if (email == null) {
            return "redirect:/login/requester"; // not logged in
        }

        model.addAttribute("request", new Request());
        return "requester-dashboard";
    }
    
    


    // ✅ Submit new request
    @PostMapping("/submit-request")
    public String submitRequest(@ModelAttribute Request request, HttpSession session) {
        String email = (String) session.getAttribute("loggedInEmail");

        if (email == null) {
            return "redirect:/login/requester";
        }

        // Fetch actual logged-in user
        User requester = userService.getUserByEmail(email).orElse(null);

        if (requester == null) {
            return "redirect:/login/requester";
        }

        request.setRequester(requester);
        requestService.saveRequest(request);
        return "redirect:/requester/view-requests";
    }

    // ✅ View all requests by this requester
    @GetMapping("/view-requests")
    public String viewRequests(Model model, HttpSession session) {
        String email = (String) session.getAttribute("loggedInEmail");

        if (email == null) {
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
