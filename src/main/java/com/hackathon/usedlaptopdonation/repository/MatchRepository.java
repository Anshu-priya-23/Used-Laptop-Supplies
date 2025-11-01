package com.hackathon.usedlaptopdonation.repository;

import com.hackathon.usedlaptopdonation.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Match entity.
 * Provides CRUD operations and custom query methods if needed.
 */
@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
}
