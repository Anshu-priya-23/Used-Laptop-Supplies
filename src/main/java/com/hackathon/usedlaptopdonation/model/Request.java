package com.hackathon.usedlaptopdonation.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity class representing a laptop request made by a requester (NGO/Student).
 * Maps to the 'requests' table in PostgreSQL.
 */
@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long requestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id", nullable = false)
    private User requester; // Reference to the User who made the request

    @Column(name = "need_count", nullable = false)
    private int needCount;

    @Column(name = "purpose", columnDefinition = "TEXT")
    private String purpose;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "contact_info", length = 100)
    private String contactInfo;

    @Column(name = "status", length = 20)
    private String status = "Pending"; // Default value

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "preferred_brand", length = 50)
    private String preferredBrand;

    @Column(name = "min_ram", length = 20)
    private String minRam;

    @Column(name = "min_storage", length = 20)
    private String minStorage;

    @Column(name = "preferred_condition", length = 50)
    private String preferredCondition;

    // Constructors
    public Request() {}

    public Request(User requester, int needCount, String purpose, String address, String contactInfo) {
        this.requester = requester;
        this.needCount = needCount;
        this.purpose = purpose;
        this.address = address;
        this.contactInfo = contactInfo;
        this.status = "Pending";
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public int getNeedCount() {
        return needCount;
    }

    public void setNeedCount(int needCount) {
        this.needCount = needCount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getPreferredBrand() {
        return preferredBrand;
    }

    public void setPreferredBrand(String preferredBrand) {
        this.preferredBrand = preferredBrand;
    }

    public String getMinRam() {
        return minRam;
    }

    public void setMinRam(String minRam) {
        this.minRam = minRam;
    }

    public String getMinStorage() {
        return minStorage;
    }

    public void setMinStorage(String minStorage) {
        this.minStorage = minStorage;
    }

    public String getPreferredCondition() {
        return preferredCondition;
    }

    public void setPreferredCondition(String preferredCondition) {
        this.preferredCondition = preferredCondition;
    }
}