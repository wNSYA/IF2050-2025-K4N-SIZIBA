package com.example.if20502025k4nsiziba.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {
    // IMPORTANT: Call DatabaseHelper.initializeDatabase() once (e.g., at app startup) to create all tables.
    private static final String DB_URL = "jdbc:sqlite:data/child_health.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initializeDatabase() {
        createChildTable();
        createFoodTable();
    }

    private static void createChildTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS child (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    gender INTEGER,
                    birth_date TEXT,
                    height REAL,
                    weight REAL,
                    head_circumference REAL,
                    hand_circumference REAL,
                    abdominal_circumference REAL,
                    date_added TEXT
                );
                """;
        String userTableSql = """
            CREATE TABLE IF NOT EXISTS users (
                username TEXT PRIMARY KEY,
                password TEXT
            );
            """;
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            stmt.execute(userTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createFoodTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS food (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    price REAL NOT NULL,
                    meal_time TEXT NOT NULL,
                    category TEXT,
                    description TEXT,
                    is_available INTEGER DEFAULT 1,
                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
                );
                """;
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}