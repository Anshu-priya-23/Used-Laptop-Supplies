package com.hackathon.usedlaptopdonation.controller;

import com.hackathon.usedlaptopdonation.service.MatchingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matching")
@CrossOrigin(origins = "*") // allows frontend access
public class MatchingController {

    private final MatchingService matchingService;

    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    

    // Execute matching via POST (for real frontend or API use)
    @PostMapping("/run")
    public ResponseEntity<String> runMatchingPost() {
        matchingService.performMatching();
        return ResponseEntity.ok("✅ Matching process executed successfully via POST.");
    }
}
