package com.hackathon.usedlaptopdonation.controller;

import com.hackathon.usedlaptopdonation.model.Laptop;
import com.hackathon.usedlaptopdonation.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/donor")
public class DonorController {

    private static final Logger logger = LoggerFactory.getLogger(DonorController.class);

    @Autowired
    private LaptopRepository laptopRepository;

    //  Donor Dashboard
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        return "vineesha/donor-dashboard";
    }

    //  Add Laptop Form
    @GetMapping("/add-laptop")
    public String showAddLaptopForm(Model model) {
        model.addAttribute("laptop", new Laptop());
        return "vineesha/add-laptop";
    }

    //  Save Laptop (fix for Whitelabel error)
    @PostMapping("/laptops/save")
    public String saveLaptop(@ModelAttribute("laptop") Laptop laptop) {
        try {
            logger.info("Saving laptop: {} {}", laptop.getBrand(), laptop.getModel());
            laptopRepository.save(laptop);
            logger.info("✅ Laptop saved successfully");
            return "redirect:/donor/view-laptops";
        } catch (Exception e) {
            logger.error("❌ Error saving laptop: ", e);
            return "error"; // optional error.html page
        }
    }

    
    @GetMapping("/view-laptops")
    public String viewLaptops(Model model) {
        model.addAttribute("laptops", laptopRepository.findAll());
        return "vineesha/view-laptops";
    }

    @GetMapping("/delete/{id}")
    public String deleteLaptop(@PathVariable("id") Long id) {
        laptopRepository.deleteById(id);
        return "redirect:/donor/view-laptops";
    }
    
 // ✅ Show Edit Form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Laptop laptop = laptopRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid laptop Id: " + id));
        model.addAttribute("laptop", laptop);
        return "vineesha/edit-laptop"; // make sure file name matches exactly
    }

    // Save Updated Laptop
    @PostMapping("/update/{id}")
    public String updateLaptop(@PathVariable("id") Long id, @ModelAttribute("laptop") Laptop updatedLaptop) {
        Laptop existingLaptop = laptopRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid laptop Id: " + id));

        // Update fields that exist
        existingLaptop.setBrand(updatedLaptop.getBrand());
        existingLaptop.setModel(updatedLaptop.getModel());
        existingLaptop.setRam(updatedLaptop.getRam());
        existingLaptop.setStorage(updatedLaptop.getStorage());

        laptopRepository.save(existingLaptop);
        return "redirect:/donor/view-laptops";
    }
    @GetMapping("/login/{role}")
    public String loginPage(@PathVariable String role, Model model) {
        model.addAttribute("role", role);

        // Donor and Requester go to registration first
        if (role.equalsIgnoreCase("donor") || role.equalsIgnoreCase("requester")) {
            return "redirect:/register/" + role;
        }

        // Admin can directly log in
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login"; // redirects to login page
    }


}