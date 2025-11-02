package com.hackathon.usedlaptopdonation.repository;

import com.hackathon.usedlaptopdonation.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing fulfilled (matched) donations.
 */
@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
}
