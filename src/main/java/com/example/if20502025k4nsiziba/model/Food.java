package com.example.if20502025k4nsiziba.model;

import java.time.LocalDateTime;

public class Food {
    private int id;
    private String name;
    private double price;
    private String mealTime;
    private String category;
    private String description;
    private boolean isAvailable;
    private LocalDateTime createdAt;

    // Constructor untuk dummy data (compatibility)
    public Food(String name, double price, String mealTime) {
        this.name = name;
        this.price = price;
        this.mealTime = mealTime;
        this.category = "Umum";
        this.description = "";
        this.isAvailable = true;
        this.createdAt = LocalDateTime.now();
    }

    // Constructor untuk insert baru
    public Food(String name, double price, String mealTime, String category, String description) {
        this.name = name;
        this.price = price;
        this.mealTime = mealTime;
        this.category = category;
        this.description = description;
        this.isAvailable = true;
        this.createdAt = LocalDateTime.now();
    }

    // Constructor lengkap untuk data dari database
    public Food(int id, String name, double price, String mealTime, String category,
                String description, boolean isAvailable, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.mealTime = mealTime;
        this.category = category;
        this.description = description;
        this.isAvailable = isAvailable;
        this.createdAt = createdAt;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getMealTime() { return mealTime; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public boolean isAvailable() { return isAvailable; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setMealTime(String mealTime) { this.mealTime = mealTime; }
    public void setCategory(String category) { this.category = category; }
    public void setDescription(String description) { this.description = description; }
    public void setAvailable(boolean available) { this.isAvailable = available; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", mealTime='" + mealTime + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", isAvailable=" + isAvailable +
                ", createdAt=" + createdAt +
                '}';
    }
}