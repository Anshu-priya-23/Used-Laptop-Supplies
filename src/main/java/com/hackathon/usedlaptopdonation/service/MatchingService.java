package com.hackathon.usedlaptopdonation.service;

import com.hackathon.usedlaptopdonation.model.*;
import com.hackathon.usedlaptopdonation.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service responsible for matching available laptops to pending requests
 * based on requester preferences (brand, RAM, storage, condition).
 */
@Service
public class MatchingService {

    private static final Logger logger = LoggerFactory.getLogger(MatchingService.class);

    private final LaptopRepository laptopRepository;
    private final RequestRepository requestRepository;
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;

    public MatchingService(LaptopRepository laptopRepository,
                           RequestRepository requestRepository,
                           MatchRepository matchRepository,
                           UserRepository userRepository) {
        this.laptopRepository = laptopRepository;
        this.requestRepository = requestRepository;
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void performMatching() {
        logger.info("Starting laptop-request matching process...");

        List<Laptop> availableLaptops = laptopRepository.findByAvailability("Available");
        List<Request> pendingRequests = requestRepository.findByStatus("Pending");

        logger.info("Found {} available laptops and {} pending requests.",
                availableLaptops.size(), pendingRequests.size());

        for (Laptop laptop : availableLaptops) {
            for (Request request : pendingRequests) {
                if (request.getNeedCount() > 0 && isMatch(laptop, request)) {

                    // Find donor user using donorId
                    Optional<User> donorOpt = userRepository.findById(laptop.getDonorId());
                    if (donorOpt.isEmpty()) {
                        logger.warn("⚠️ Donor not found for Laptop ID {}", laptop.getLaptopId());
                        continue;
                    }

                    Match match = new Match();
                    match.setLaptop(laptop);
                    match.setDonor(donorOpt.get());
                    match.setRequester(request.getRequester());
                    match.setStatus("Matched");

                    matchRepository.save(match);

                    // Update statuses
                    laptop.setAvailability("Assigned");
                    request.setNeedCount(request.getNeedCount() - 1);
                    if (request.getNeedCount() == 0) {
                        request.setStatus("Fulfilled");
                    }

                    laptopRepository.save(laptop);
                    requestRepository.save(request);

                    logger.info("✅ Matched Laptop ID {} ({} / {} / {}) with Request ID {} by {}",
                            laptop.getLaptopId(),
                            laptop.getBrand(),
                            laptop.getRam(),
                            laptop.getConditionStatus(),
                            request.getRequestId(),
                            request.getRequester().getName());

                    break; // move to next laptop
                }
            }
        }

        logger.info("✅ Matching process completed successfully.");
    }

    private boolean isMatch(Laptop laptop, Request request) {
        // Brand match
        if (request.getPreferredBrand() != null &&
                !request.getPreferredBrand().isEmpty() &&
                !laptop.getBrand().equalsIgnoreCase(request.getPreferredBrand())) {
            return false;
        }

        // RAM check
        if (request.getMinRam() != null && !request.getMinRam().isEmpty()) {
            try {
                int laptopRam = extractNumber(laptop.getRam());
                int requiredRam = extractNumber(request.getMinRam());
                if (laptopRam < requiredRam) return false;
            } catch (NumberFormatException e) {
                logger.warn("Invalid RAM format. Laptop: {}, Request: {}", laptop.getRam(), request.getMinRam());
            }
        }

        // Storage check
        if (request.getMinStorage() != null && !request.getMinStorage().isEmpty()) {
            try {
                int laptopStorage = extractNumber(laptop.getStorage());
                int requiredStorage = extractNumber(request.getMinStorage());
                if (laptopStorage < requiredStorage) return false;
            } catch (NumberFormatException e) {
                logger.warn("Invalid storage format. Laptop: {}, Request: {}", laptop.getStorage(), request.getMinStorage());
            }
        }

        // Condition check
        if (request.getPreferredCondition() != null &&
                !request.getPreferredCondition().isEmpty() &&
                !laptop.getConditionStatus().equalsIgnoreCase(request.getPreferredCondition())) {
            return false;
        }

        return true;
    }

    private int extractNumber(String value) {
        return Integer.parseInt(value.replaceAll("[^0-9]", ""));
    }
}
