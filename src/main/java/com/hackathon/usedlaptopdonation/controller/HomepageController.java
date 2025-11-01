package com.hackathon.usedlaptopdonation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomepageController {

    // HOMEPAGE
    @GetMapping("/homepage")
    public String home() {
        return "homepage"; // homepage.html
    }

    // LOGIN PAGE (Dynamic Role)
    @GetMapping("/login/{role}")
    public String loginPage(@PathVariable String role, Model model) {
        model.addAttribute("role", role);
        return "login"; // login.html
    }

    // PROCESS LOGIN
    @PostMapping("/process-login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String role,
                               Model model) {
        if (username.equals("admin") && password.equals("admin")) {
            return "redirect:/admin/dashboard";
        } else if (role.equalsIgnoreCase("donor")) {
            return "redirect:/donor/dashboard";
        } else if (role.equalsIgnoreCase("requester")) {
            return "redirect:/requester/dashboard";
        } else {
            model.addAttribute("error", "Invalid credentials!");
            model.addAttribute("role", role);
            return "login";
        }
    }

    // REGISTER PAGE (Dynamic Role)
    @GetMapping("/register/{role}")
    public String showRegisterPage(@PathVariable String role, Model model) {
        model.addAttribute("role", role);
        return "register"; // register.html
    }

    // PROCESS REGISTRATION
    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String role,
                               Model model) {
        System.out.println("New user registered: " + username + " (" + role + ")");
        model.addAttribute("message", "Registration successful!");
        model.addAttribute("role", role);
        return "login"; // go to login page
    }

    // DASHBOARDS
    @GetMapping("/admin/dashboard")
    public String adminDashboard() { return "admin-dashboard"; }

    @GetMapping("/donor/dashboard")
    public String donorDashboard() { return "donor-dashboard"; }

    @GetMapping("/requester/dashboard")
    public String requesterDashboard() { return "requester-dashboard"; }
}
