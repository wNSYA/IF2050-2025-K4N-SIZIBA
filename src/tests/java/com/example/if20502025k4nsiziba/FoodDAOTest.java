package com.example.if20502025k4nsiziba;

import com.example.if20502025k4nsiziba.database.DatabaseHelper;
import com.example.if20502025k4nsiziba.model.Food;
import com.example.if20502025k4nsiziba.model.FoodDAO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FoodDAOTest {

    private static final FoodDAO foodDAO = new FoodDAO();

    @BeforeAll
    static void setUp() throws SQLException {
        // Initialize the database once for all tests in this class
        DatabaseHelper.initializeDatabase();
        clearFoodTable(); // Ensure table is clean before populating

        // Populate with known data for predictable test results
        foodDAO.insertFood(new Food("Bubur Ayam", 12000, "Pagi", "Sarapan", "Bubur ayam spesial"));
        foodDAO.insertFood(new Food("Roti Bakar", 8000, "Pagi", "Sarapan", "Roti bakar keju"));
        foodDAO.insertFood(new Food("Nasi Goreng", 15000, "Siang", "Makan Siang", "Nasi goreng spesial"));
        foodDAO.insertFood(new Food("Soto Ayam", 18000, "Siang", "Makan Siang", "Soto ayam Lamongan"));
        foodDAO.insertFood(new Food("Ayam Bakar", 25000, "Malam", "Makan Malam", "Ayam bakar madu"));
    }

    @AfterAll
    static void tearDown() throws SQLException {
        // Clean up the food table after all tests are done
        clearFoodTable();
    }

    private static void clearFoodTable() throws SQLException {
        try (Connection conn = DatabaseHelper.connect(); Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM food");
            stmt.execute("DELETE FROM sqlite_sequence WHERE name = 'food'");
        }
    }

    @Test
    void testGetAllFoods() {
        List<Food> allFoods = foodDAO.getAllFoods();
        assertNotNull(allFoods);
        assertEquals(5, allFoods.size(), "Should retrieve all inserted food items.");
    }

    @Test
    void testGetFoodsByMealTime() {
        // Act: Get recommendations for "Pagi" (Breakfast)
        List<Food> pagiFoods = foodDAO.getFoodsByMealTime("Pagi");

        // Assert
        assertNotNull(pagiFoods);
        assertEquals(2, pagiFoods.size());
        // Verify that only breakfast items are returned
        assertTrue(pagiFoods.stream().allMatch(food -> food.getMealTime().equals("Pagi")));
    }

    @Test
    void testGetFoodsByMealTimeAndBudget() {
        // Act: Get "Siang" (Lunch) recommendations with a budget of 16000
        List<Food> lunchFoods = foodDAO.getFoodsByMealTimeAndBudget("Siang", 16000);

        // Assert
        assertNotNull(lunchFoods);
        assertEquals(1, lunchFoods.size(), "Should only return foods within the budget.");
        assertEquals("Nasi Goreng", lunchFoods.get(0).getName());
        assertTrue(lunchFoods.get(0).getPrice() <= 16000);
    }

    @Test
    void testGetFoodsByBudget_ShouldReturnEmptyListIfBudgetIsTooLow() {
        // Act: Get "Malam" (Dinner) recommendations with a budget that's too low
        List<Food> dinnerFoods = foodDAO.getFoodsByMealTimeAndBudget("Malam", 20000);

        // Assert
        assertNotNull(dinnerFoods);
        assertTrue(dinnerFoods.isEmpty(), "Should return an empty list if no food matches the budget.");
    }

    @Test
    void testGetFoodById() {
        // Arrange: Get the ID of a known food item
        List<Food> rotiList = foodDAO.getFoodsByMealTimeAndBudget("Pagi", 9000);
        assertFalse(rotiList.isEmpty());
        int rotiId = rotiList.get(0).getId();

        // Act
        Food retrievedFood = foodDAO.getFoodById(rotiId);

        // Assert
        assertNotNull(retrievedFood);
        assertEquals("Roti Bakar", retrievedFood.getName());
    }
}
