package com.hackathon.usedlaptopdonation.service;

import com.hackathon.usedlaptopdonation.model.Laptop;
import com.hackathon.usedlaptopdonation.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private AdminRepository adminRepository;

    public List<Laptop> getPendingDonations() {
        logger.info("Fetching pending laptop donations...");
        List<Laptop> pendingLaptops = adminRepository.findByAvailability("Pending");
        logger.info("Found {} pending donations", pendingLaptops.size());
        return pendingLaptops;
    }

    public void approveDonation(Long laptopId) {
        logger.info("Approving laptop donation {}", laptopId);
        adminRepository.findById(laptopId).ifPresent(laptop -> {
            laptop.setAvailability("Available"); // ✅ Set to "Available" for matching
            adminRepository.save(laptop);
            logger.info("Laptop {} approved and marked as Available", laptopId);
        });
    }

    public void rejectDonation(Long laptopId) {
        logger.info("Rejecting laptop donation {}", laptopId);
        adminRepository.findById(laptopId).ifPresent(laptop -> {
            laptop.setAvailability("Rejected");
            adminRepository.save(laptop);
            logger.info("Laptop {} rejected", laptopId);
        });
    }
}
