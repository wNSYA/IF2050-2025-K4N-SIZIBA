package com.example.if20502025k4nsiziba;

import com.example.if20502025k4nsiziba.model.Food;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    @Test
    void testFullConstructorAndGetters() {
        // 1. Arrange: Define all food properties
        int id = 1;
        String name = "Nasi Tim";
        double price = 15000;
        String mealTime = "Pagi";
        String category = "Makanan Bayi";
        String description = "Nasi tim lembut dengan ayam dan wortel.";
        boolean isAvailable = true;
        LocalDateTime createdAt = LocalDateTime.now();

        // 2. Act: Create a Food object using the full constructor
        Food food = new Food(id, name, price, mealTime, category, description, isAvailable, createdAt);

        // 3. Assert: Verify all getters return the correct values
        assertEquals(id, food.getId());
        assertEquals(name, food.getName());
        assertEquals(price, food.getPrice());
        assertEquals(mealTime, food.getMealTime());
        assertEquals(category, food.getCategory());
        assertEquals(description, food.getDescription());
        assertTrue(food.isAvailable());
        assertEquals(createdAt, food.getCreatedAt());
    }

    @Test
    void testSimplifiedConstructor() {
        // 1. Arrange: Define properties for the simpler constructor
        String name = "Salad Buah";
        double price = 20000;
        String mealTime = "Siang";

        // 2. Act: Create a Food object using the simplified constructor
        Food food = new Food(name, price, mealTime);

        // 3. Assert: Verify the explicitly set properties and the default values
        assertEquals(name, food.getName());
        assertEquals(price, food.getPrice());
        assertEquals(mealTime, food.getMealTime());
        assertEquals("Umum", food.getCategory(), "Category should default to 'Umum'");
        assertEquals("", food.getDescription(), "Description should default to an empty string.");
        assertTrue(food.isAvailable(), "isAvailable should default to true.");
        assertNotNull(food.getCreatedAt(), "createdAt should be set automatically.");
    }
}
