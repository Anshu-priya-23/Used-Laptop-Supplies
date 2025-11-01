package com.hackathon.usedlaptopdonation.repository;

import com.hackathon.usedlaptopdonation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for User entity.
 * Provides CRUD operations and custom queries if needed.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom finder method for login
    User findByEmail(String email);

    // Optional: find by email and password (for basic login)
    User findByEmailAndPassword(String email, String password);
}