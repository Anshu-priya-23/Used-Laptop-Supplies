package com.hackathon.usedlaptopdonation.service;

import com.hackathon.usedlaptopdonation.model.Laptop;
import com.hackathon.usedlaptopdonation.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Service layer for Admin operations.
 * Handles business logic for managing donations and requests.
 */
@Service
public class AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private AdminRepository adminRepository;

    /**
     * Retrieve all pending laptop donations.
     *
     * @return List of pending laptop donations.
     */
    public List<Laptop> getPendingDonations() {
        logger.info("Fetching all pending laptop donations...");
        List<Laptop> pendingLaptops = adminRepository.findByStatus("Pending");
        logger.info("Found {} pending donations", pendingLaptops.size());
        return pendingLaptops;
    }

    /**
     * Approve a specific laptop donation by ID.
     *
     * @param laptopId The ID of the laptop donation to approve.
     */
    public void approveDonation(Long laptopId) {
        logger.info("Approving laptop donation with ID: {}", laptopId);
        adminRepository.findById(laptopId).ifPresent(laptop -> {
            laptop.setStatus("Approved");
            adminRepository.save(laptop);
            logger.info("Laptop donation {} approved successfully", laptopId);
        });
    }

    /**
     * Reject a specific laptop donation by ID.
     *
     * @param laptopId The ID of the laptop donation to reject.
     */
    public void rejectDonation(Long laptopId) {
        logger.info("Rejecting laptop donation with ID: {}", laptopId);
        adminRepository.findById(laptopId).ifPresent(laptop -> {
            laptop.setStatus("Rejected");
            adminRepository.save(laptop);
            logger.info("Laptop donation {} rejected successfully", laptopId);
        });
    }
}
