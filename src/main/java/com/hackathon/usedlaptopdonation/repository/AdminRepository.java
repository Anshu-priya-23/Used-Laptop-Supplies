package com.hackathon.usedlaptopdonation.repository;

import com.hackathon.usedlaptopdonation.model.Laptop;
import com.hackathon.usedlaptopdonation.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for Admin data operations.
 * Provides database access methods for Laptop and Request entities.
 */
@Repository
public interface AdminRepository extends JpaRepository<Laptop, Long> {

    /**
     * Fetch all pending laptop donations.
     *
     * @return List of laptops with pending approval.
     */
	List<Laptop> findByAvailability(String availability);

}

