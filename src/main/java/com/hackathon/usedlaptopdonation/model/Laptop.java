package com.hackathon.usedlaptopdonation.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "laptops")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laptop_id")
    private Long laptopId;

    @Column(name = "donor_id")
    private Long donorId;

    @Column(length = 50)
    private String brand;

    @Column(length = 50)
    private String model;

    @Column(length = 20)
    private String ram;

    @Column(length = 20)
    private String storage;

    @Column(name = "condition", length = 50)
    private String conditionStatus;

    @Column(length = 100)
    private String location;

    @Column(length = 20)
    private String availability = "Available";

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // ---- Constructors ----
    public Laptop() {
    }

    public Laptop(Long donorId, String brand, String model, String ram, String storage,
                  String conditionStatus, String location, String availability) {
        this.donorId = donorId;
        this.brand = brand;
        this.model = model;
        this.ram = ram;
        this.storage = storage;
        this.conditionStatus = conditionStatus;
        this.location = location;
        this.availability = availability;
    }

    // ---- Getters and Setters ----
    public Long getLaptopId() {
        return laptopId;
    }

    public void setLaptopId(Long laptopId) {
        this.laptopId = laptopId;
    }

    public Long getDonorId() {
        return donorId;
    }

    public void setDonorId(Long donorId) {
        this.donorId = donorId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getConditionStatus() {
        return conditionStatus;
    }

    public void setConditionStatus(String conditionStatus) {
        this.conditionStatus = conditionStatus;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}