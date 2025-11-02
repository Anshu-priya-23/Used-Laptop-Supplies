package com.hackathon.usedlaptopdonation.repository;

import com.hackathon.usedlaptopdonation.model.Request;

import com.hackathon.usedlaptopdonation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByRequester(User requester);
    List<Request> findByStatus(String status);
}


/**
 * Repository for managing Request entities.
 */


