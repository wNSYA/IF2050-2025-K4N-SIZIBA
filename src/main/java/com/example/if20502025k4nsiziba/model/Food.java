package com.example.if20502025k4nsiziba.model;

public class Food {
    private String name;
    private double price;
    private String mealTime;

    public Food(String name, double price, String mealTime) {
        this.name = name;
        this.price = price;
        this.mealTime = mealTime;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getMealTime() { return mealTime; }
}
