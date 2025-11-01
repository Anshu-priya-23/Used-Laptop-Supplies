package com.hackathon.usedlaptopdonation.service;

import com.hackathon.usedlaptopdonation.model.Request;
import com.hackathon.usedlaptopdonation.model.User;
import com.hackathon.usedlaptopdonation.repository.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public Request saveRequest(Request request) {
        return requestRepository.save(request);
    }

    public List<Request> getRequestsByUser(User requester) {
        return requestRepository.findByRequester(requester);
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }
}
