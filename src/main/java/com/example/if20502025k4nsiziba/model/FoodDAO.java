package com.example.if20502025k4nsiziba.model;

import com.example.if20502025k4nsiziba.database.DatabaseHelper;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO {

    public void insertFood(Food food) {
        String sql = """
        INSERT INTO food (name, price, meal_time, category, description, is_available, created_at)
        VALUES (?, ?, ?, ?, ?, ?, ?);
        """;

        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, food.getName());
            stmt.setDouble(2, food.getPrice());
            stmt.setString(3, food.getMealTime());
            stmt.setString(4, food.getCategory());
            stmt.setString(5, food.getDescription());
            stmt.setInt(6, food.isAvailable() ? 1 : 0);
            stmt.setString(7, food.getCreatedAt().toString());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        food.setId(generatedKeys.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Food getFoodById(int id) {
        String sql = "SELECT * FROM food WHERE id = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return buildFoodFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Food> getAllFoods() {
        List<Food> list = new ArrayList<>();
        String sql = "SELECT * FROM food WHERE is_available = 1 ORDER BY meal_time, price";
        try (Connection conn = DatabaseHelper.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(buildFoodFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Food> getFoodsByMealTime(String mealTime) {
        List<Food> list = new ArrayList<>();
        String sql = "SELECT * FROM food WHERE meal_time = ? AND is_available = 1 ORDER BY price";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mealTime);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(buildFoodFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Food> getFoodsByMealTimeAndBudget(String mealTime, double maxBudget) {
        List<Food> list = new ArrayList<>();
        String sql = "SELECT * FROM food WHERE meal_time = ? AND price <= ? AND is_available = 1 ORDER BY price";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mealTime);
            stmt.setDouble(2, maxBudget);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(buildFoodFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateFood(Food food) {
        String sql = """
            UPDATE food SET
                name = ?, price = ?, meal_time = ?, category = ?, 
                description = ?, is_available = ?
            WHERE id = ?;
            """;
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, food.getName());
            stmt.setDouble(2, food.getPrice());
            stmt.setString(3, food.getMealTime());
            stmt.setString(4, food.getCategory());
            stmt.setString(5, food.getDescription());
            stmt.setInt(6, food.isAvailable() ? 1 : 0);
            stmt.setInt(7, food.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFood(int id) {
        String sql = "DELETE FROM food WHERE id = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void softDeleteFood(int id) {
        String sql = "UPDATE food SET is_available = 0 WHERE id = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertDummyData() {
        // Check if data already exists
        String checkSql = "SELECT COUNT(*) FROM food";
        try (Connection conn = DatabaseHelper.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(checkSql)) {

            if (rs.next() && rs.getInt(1) > 0) {
                return; // Data already exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Insert dummy data
        List<Food> dummyFoods = List.of(
                new Food("Bubur Bayi", 15000, "Pagi", "Makanan Bayi", "Bubur lembut untuk bayi dengan nutrisi lengkap"),
                new Food("Roti Telur", 10000, "Pagi", "Sarapan", "Roti panggang dengan telur mata sapi"),
                new Food("Nasi Gudeg", 12000, "Pagi", "Makanan Tradisional", "Gudeg khas Yogya dengan nasi putih"),
                new Food("Pancake", 18000, "Pagi", "Sarapan", "Pancake fluffy dengan sirup maple"),
                new Food("Sandwich", 8000, "Pagi", "Sarapan", "Sandwich isi sayuran segar"),

                new Food("Dory Steak", 29000, "Siang", "Makanan Barat", "Dory fish steak dengan saus lemon"),
                new Food("Ayam Kukus", 25000, "Siang", "Makanan Sehat", "Ayam kukus dengan bumbu rempah"),
                new Food("Nasi Padang", 20000, "Siang", "Makanan Tradisional", "Nasi dengan lauk pauk khas Padang"),
                new Food("Soto Ayam", 15000, "Siang", "Makanan Berkuah", "Soto ayam dengan kuah bening segar"),
                new Food("Gado-gado", 12000, "Siang", "Makanan Sehat", "Sayuran segar dengan bumbu kacang"),
                new Food("Bakso", 13000, "Siang", "Makanan Berkuah", "Bakso sapi dengan mie dan sayuran"),

                new Food("Daging Lembut", 19000, "Malam", "Makanan Utama", "Daging sapi empuk dengan bumbu special"),
                new Food("Sup Sayur", 15000, "Malam", "Makanan Sehat", "Sup sayuran segar dan bergizi"),
                new Food("Ayam Bakar", 22000, "Malam", "Makanan Utama", "Ayam bakar dengan bumbu kecap manis"),
                new Food("Pecel Lele", 18000, "Malam", "Makanan Tradisional", "Lele goreng dengan sambal pecel"),
                new Food("Cap Cay", 16000, "Malam", "Makanan Sehat", "Tumisan sayuran dengan saus tiram"),
                new Food("Mie Ayam", 14000, "Malam", "Makanan Berkuah", "Mie ayam dengan pangsit dan bakso")
        );

        for (Food food : dummyFoods) {
            insertFood(food);
        }
    }

    private Food buildFoodFromResultSet(ResultSet rs) throws SQLException {
        return new Food(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getString("meal_time"),
                rs.getString("category"),
                rs.getString("description"),
                rs.getInt("is_available") == 1,
                LocalDateTime.parse(rs.getString("created_at"))
        );
    }
}