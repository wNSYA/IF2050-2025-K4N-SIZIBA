package com.example.if20502025k4nsiziba.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {
    //  IMPORTANT: Call DatabaseHelper.initializeDatabase() once (e.g., at app startup) to create the tables.
    private static final String DB_URL = "jdbc:sqlite:data/child_health.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initializeDatabase() {
        String createChildTableSql = """
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
        String createIllnessTableSql = """
                CREATE TABLE IF NOT EXISTS child_illness (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    child_id INTEGER NOT NULL,
                    illness_name TEXT NOT NULL,
                    description TEXT,
                    date_of_illness TEXT NOT NULL,
                    FOREIGN KEY (child_id) REFERENCES child (id) ON DELETE CASCADE
                );
                """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createChildTableSql);
            stmt.execute(createIllnessTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}