package com.hackathon.usedlaptopdonation.repository;

import com.hackathon.usedlaptopdonation.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    List<Laptop> findByAvailability(String availability);
}
