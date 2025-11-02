package com.hackathon.usedlaptopdonation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomepageController {

    @GetMapping("/homepage")
    public String home(@RequestParam(value = "role", required = false) String role, Model model) {
        model.addAttribute("selectedRole", role);
        return "homepage"; // homepage.html
    }

    // LOGIN PAGE (Dynamic Role)
    @GetMapping("/login/{role}")
    public String loginPage(@PathVariable String role, Model model) {
        model.addAttribute("role", role);
        return "login"; // login.html
    }

    // ✅ PROCESS LOGIN + DEBUG
    @PostMapping("/process-login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String role,
                               Model model,
                               HttpSession session) {

        System.out.println("🟢 Login attempt: " + username + " (" + role + ")");

        // ✅ Save logged-in email/username to session
        session.setAttribute("loggedInEmail", username);
        System.out.println("✅ Session saved: loggedInEmail = " + session.getAttribute("loggedInEmail"));

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
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String role,
                               Model model) {
        System.out.println("🟢 Registered new user: " + username + " (" + role + ")");
        model.addAttribute("message", "Registration successful!");
        model.addAttribute("role", role);
        return "login";
    }

    // DASHBOARDS
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }
}
