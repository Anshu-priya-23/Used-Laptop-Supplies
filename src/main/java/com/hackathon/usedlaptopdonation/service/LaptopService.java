package com.hackathon.usedlaptopdonation.service;

import com.hackathon.usedlaptopdonation.model.Laptop;
import com.hackathon.usedlaptopdonation.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopService {

    private static final Logger logger = LoggerFactory.getLogger(LaptopService.class);

    @Autowired
    private LaptopRepository laptopRepository;

    // ➕ Add a new laptop
    public Laptop addLaptop(Laptop laptop) {
        logger.info("Adding new laptop: {} {}", laptop.getBrand(), laptop.getModel());
        return laptopRepository.save(laptop);
    }

    // 📋 Get all laptops
    public List<Laptop> getAllLaptops() {
        logger.info("Fetching all laptops from DB");
        return laptopRepository.findAll();
    }

    // ✅ Get available laptops only
    public List<Laptop> getAvailableLaptops() {
        logger.info("Fetching available laptops");
        return laptopRepository.findByAvailability("Available");
    }

    // 👤 Get laptops by donorId
    public List<Laptop> getLaptopsByDonor(Long donorId) {
        logger.info("Fetching laptops for donor ID: {}", donorId);
        return laptopRepository.findByDonorId(donorId);
    }

    // 🆔 Get laptop by ID  ✅ (Fix for your Controller)
    public Laptop getLaptopById(Long id) {
        logger.info("Fetching laptop with ID: {}", id);
        Optional<Laptop> optionalLaptop = laptopRepository.findById(id);
        return optionalLaptop.orElse(null);
    }

    // ✏️ Update existing laptop
    public Laptop updateLaptop(Long id, Laptop updatedLaptop) {
        logger.info("Updating laptop with ID: {}", id);

        Optional<Laptop> optionalLaptop = laptopRepository.findById(id);
        if (optionalLaptop.isPresent()) {
            Laptop laptop = optionalLaptop.get();
            laptop.setBrand(updatedLaptop.getBrand());
            laptop.setModel(updatedLaptop.getModel());
            laptop.setRam(updatedLaptop.getRam());
            laptop.setStorage(updatedLaptop.getStorage());
            laptop.setConditionStatus(updatedLaptop.getConditionStatus());
            laptop.setLocation(updatedLaptop.getLocation());
            laptop.setAvailability(updatedLaptop.getAvailability());
            return laptopRepository.save(laptop);
        } else {
            logger.error("Laptop not found with ID {}", id);
            throw new RuntimeException("Laptop not found with ID: " + id);
        }
    }

    // ❌ Delete a laptop
    public void deleteLaptop(Long id) {
        logger.info("Deleting laptop with ID: {}", id);
        laptopRepository.deleteById(id);
    }
}
