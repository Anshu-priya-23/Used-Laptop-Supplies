package com.hackathon.usedlaptopdonation.service;


import com.hackathon.usedlaptopdonation.model.User;
import com.hackathon.usedlaptopdonation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

/**
 * Service class for managing User operations.
 * Handles business logic like registration, login validation, etc.
 */
@Service
public class UserService {

    private static final Logger log = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository userRepository;

    /**
     * Registers a new user.
     * @param user User object containing name, email, password, and role
     * @return saved User object
     */
    public User registerUser(User user) {
        log.info("Attempting to register user with email: " + user.getEmail());

        // Check if user already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            log.warning("User already exists with email: " + user.getEmail());
            return null;
        }

        // Save new user
        User savedUser = userRepository.save(user);
        log.info("User registered successfully with ID: " + savedUser.getUserId());
        return savedUser;
    }

    /**
     * Validates user login.
     * @param email user's email
     * @param password user's password
     * @return User if credentials match, null otherwise
     */
    public User validateLogin(String email, String password) {
        log.info("Attempting login for user: " + email);
        User user = userRepository.findByEmailAndPassword(email, password);

        if (user == null) {
            log.warning("Login failed for user: " + email);
        } else {
            log.info("Login successful for user: " + email);
        }

        return user;
    }

    /**
     * Fetch a user by email.
     * @param email user's email
     * @return Optional<User>
     */
    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }
}