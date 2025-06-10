package com.example.if20502025k4nsiziba.controller;

import com.example.if20502025k4nsiziba.model.Food;
import com.example.if20502025k4nsiziba.model.FoodDAO;
import java.util.*;

public class FoodController {

    private final FoodDAO foodDAO;

    public FoodController() {
        this.foodDAO = new FoodDAO();
        // Initialize dummy data if database is empty
        initializeDummyDataIfNeeded();
    }

    private void initializeDummyDataIfNeeded() {
        try {
            foodDAO.insertDummyData();
        } catch (Exception e) {
            System.err.println("Error initializing dummy data: " + e.getMessage());
        }
    }

    public List<Food> getAllFoods() {
        return foodDAO.getAllFoods();
    }

    public List<Food> getFoodsByMealTime(String mealTime) {
        return foodDAO.getFoodsByMealTime(mealTime);
    }

    public List<Food> getFoodRecommendationsByBudget(String mealTime, double budget) {
        return foodDAO.getFoodsByMealTimeAndBudget(mealTime, budget);
    }

    public void addFood(Food food) {
        foodDAO.insertFood(food);
    }

    public void updateFood(Food food) {
        foodDAO.updateFood(food);
    }

    public void deleteFood(int id) {
        foodDAO.deleteFood(id);
    }

    public Food getFoodById(int id) {
        return foodDAO.getFoodById(id);
    }

    // Method untuk compatibility dengan kode lama (deprecated)
    @Deprecated
    public List<Food> getDummyFoods() {
        return getAllFoods();
    }

    // Method untuk compatibility dengan kode lama (deprecated)
    @Deprecated
    public List<Food> getRecommendationsByBudget(double budget) {
        double portion = budget / 3.0;
        List<Food> result = new ArrayList<>();

        List<Food> pagi = foodDAO.getFoodsByMealTimeAndBudget("Pagi", portion);
        List<Food> siang = foodDAO.getFoodsByMealTimeAndBudget("Siang", portion);
        List<Food> malam = foodDAO.getFoodsByMealTimeAndBudget("Malam", portion);

        if (!pagi.isEmpty()) result.add(pagi.get(0));
        if (!siang.isEmpty()) result.add(siang.get(0));
        if (!malam.isEmpty()) result.add(malam.get(0));

        return result;
    }
}