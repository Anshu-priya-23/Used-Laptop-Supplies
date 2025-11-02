package com.hackathon.usedlaptopdonation.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity class representing a donated laptop in the system.
 * 
 * <p>This class maps to the 'laptops' table in the database
 * and stores details about each donated laptop such as brand,
 * model, specifications, and condition.</p>
 * 
 * @author Anushka
 */
@Entity
@Table(name = "laptops")
public class Laptop {

    /** Primary key for Laptop entity */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laptop_id")
    private Long laptopId;

    /** Foreign key referencing the donor who donated this laptop */
    @Column(name = "donor_id")
    private Long donorId;

    /** Brand name of the laptop */
    @Column(length = 50)
    private String brand;

    /** Model name of the laptop */
    @Column(length = 50)
    private String model;

    /** RAM specification (e.g., 8GB, 16GB) */
    @Column(length = 20)
    private String ram;

    /** Storage specification (e.g., 512GB SSD, 1TB HDD) */
    @Column(length = 20)
    private String storage;

    /** Condition of the laptop (e.g., New, Used, Good) */
    @Column(name = "condition", length = 50)
    private String conditionStatus;

    /** Physical location or pickup area of the laptop */
    @Column(length = 100)
    private String location;

    /** Current status of the laptop (Available, Approved, Donated, etc.) */
    @Column(length = 20)
    private String status = "Available";

    /** Timestamp when the laptop record was created */
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // ---- Constructors ----

    /** Default constructor for JPA */
    public Laptop() {}

    /**
     * Parameterized constructor for creating a new Laptop record.
     *
     * @param donorId Donor ID associated with this laptop
     * @param brand Laptop brand
     * @param model Laptop model
     * @param ram Laptop RAM specification
     * @param storage Laptop storage specification
     * @param conditionStatus Laptop condition
     * @param location Laptop location
     * @param status Current status of the laptop
     */
    public Laptop(Long donorId, String brand, String model, String ram, String storage,
                  String conditionStatus, String location, String status) {
        this.donorId = donorId;
        this.brand = brand;
        this.model = model;
        this.ram = ram;
        this.storage = storage;
        this.conditionStatus = conditionStatus;
        this.location = location;
        this.status = status;
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
}
