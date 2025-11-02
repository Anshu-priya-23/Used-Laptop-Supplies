package com.hackathon.usedlaptopdonation.repository;

import com.hackathon.usedlaptopdonation.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository for managing Request entities.
 */
@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByStatus(String status);
}
