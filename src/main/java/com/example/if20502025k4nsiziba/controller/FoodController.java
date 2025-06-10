package com.example.if20502025k4nsiziba.controller;

import com.example.if20502025k4nsiziba.model.Food;
import java.util.*;
import java.util.stream.Collectors;

public class FoodController {

    public List<Food> getDummyFoods() {
        return List.of(
                new Food("Bubur Bayi", 15000, "Pagi"),
                new Food("Roti Telur", 10000, "Pagi"),
                new Food("Dory Steak", 29000, "Siang"),
                new Food("Ayam Kukus", 25000, "Siang"),
                new Food("Daging Lembut", 19000, "Malam"),
                new Food("Sup Sayur", 15000, "Malam")
        );
    }

    public List<Food> getRecommendationsByBudget(double budget) {
        double portion = budget / 3.0;
        List<Food> allFoods = getDummyFoods();

        List<Food> pagi = allFoods.stream().filter(f -> f.getMealTime().equals("Pagi") && f.getPrice() <= portion).limit(1).collect(Collectors.toList());
        List<Food> siang = allFoods.stream().filter(f -> f.getMealTime().equals("Siang") && f.getPrice() <= portion).limit(1).collect(Collectors.toList());
        List<Food> malam = allFoods.stream().filter(f -> f.getMealTime().equals("Malam") && f.getPrice() <= portion).limit(1).collect(Collectors.toList());

        List<Food> result = new ArrayList<>();
        result.addAll(pagi);
        result.addAll(siang);
        result.addAll(malam);
        return result;
    }
}

