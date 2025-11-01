package com.hackathon.usedlaptopdonation.repository;

import com.hackathon.usedlaptopdonation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
