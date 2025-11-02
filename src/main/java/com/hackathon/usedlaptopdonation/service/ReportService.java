package com.hackathon.usedlaptopdonation.service;

import com.hackathon.usedlaptopdonation.model.Laptop;
import com.hackathon.usedlaptopdonation.model.Request;
import com.hackathon.usedlaptopdonation.model.Match;
import com.hackathon.usedlaptopdonation.repository.LaptopRepository;
import com.hackathon.usedlaptopdonation.repository.RequestRepository;
import com.hackathon.usedlaptopdonation.repository.MatchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Service layer for fetching report-related data for the admin panel.
 * This service interacts with repositories to retrieve donation,
 * request, and fulfillment data for reporting purposes.
 *
 * @author Anushka
 */
@Service
public class ReportService {

    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private MatchRepository matchRepository;

    /**
     * Fetch all donated laptops.
     *
     * @return List of all donated laptops.
     */
    public List<Laptop> getAllDonations() {
        logger.info("Fetching all laptop donations...");
        return laptopRepository.findAll();
    }

    /**
     * Fetch all pending laptop requests.
     *
     * @return List of pending laptop requests.
     */
    public List<Request> getPendingRequests() {
        logger.info("Fetching all pending laptop requests...");
        return requestRepository.findByStatus("Pending");
    }

    /**
     * Fetch all fulfilled donations (matches).
     *
     * @return List of fulfilled donations.
     */
    public List<Match> getFulfilledDonations() {
        logger.info("Fetching all fulfilled donations...");
        return matchRepository.findAll();
    }
}
