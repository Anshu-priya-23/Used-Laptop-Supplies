package com.hackathon.usedlaptopdonation.repository;

import com.hackathon.usedlaptopdonation.model.Request;
import com.hackathon.usedlaptopdonation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByRequester(User requester);
}
