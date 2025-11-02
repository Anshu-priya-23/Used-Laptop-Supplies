package com.hackathon.usedlaptopdonation.repository;

import com.hackathon.usedlaptopdonation.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    List<Laptop> findByAvailability(String availability);
    List<Laptop> findByDonorId(Long donorId);
}
