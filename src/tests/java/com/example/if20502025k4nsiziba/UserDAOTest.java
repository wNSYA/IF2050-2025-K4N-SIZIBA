package com.example.if20502025k4nsiziba;

import com.example.if20502025k4nsiziba.database.DatabaseHelper;
import com.example.if20502025k4nsiziba.model.User;
import com.example.if20502025k4nsiziba.model.UserDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    @BeforeEach
    void setUp() throws SQLException {
        // Ensure the database is initialized and clean before each test
        DatabaseHelper.initializeDatabase();
        clearDatabaseTables();
    }

    @AfterEach
    void tearDown() throws SQLException {
        // Clean up after each test to maintain isolation
        clearDatabaseTables();
    }

    private void clearDatabaseTables() throws SQLException {
        try (Connection conn = DatabaseHelper.connect(); Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM child_illness");
            stmt.execute("DELETE FROM child");
            stmt.execute("DELETE FROM users");
            stmt.execute("DELETE FROM sqlite_sequence WHERE name IN ('child_illness', 'child', 'users')");
        }
    }

    @Test
    void testSuccessfulRegisterAndLogin() {
        // 1. Act: Register a new user
        boolean isRegistered = UserDAO.register("Budi Setiawan", "budi", "password123");

        // 2. Assert: Registration should be successful
        assertTrue(isRegistered, "User should be successfully registered.");

        // 3. Act: Try to log in with the correct credentials
        User loggedInUser = UserDAO.login("budi", "password123");

        // 4. Assert: Login should be successful and return the correct user object
        assertNotNull(loggedInUser, "Login should be successful with correct credentials.");
        assertEquals("Budi Setiawan", loggedInUser.getName());
        assertEquals("budi", loggedInUser.getUsername());
    }

    @Test
    void testLoginWithIncorrectPassword() {
        // 1. Arrange: Register a user first
        UserDAO.register("Siti Aminah", "siti", "correct_password");

        // 2. Act: Attempt to log in with the wrong password
        User loggedInUser = UserDAO.login("siti", "wrong_password");

        // 3. Assert: Login should fail, returning null
        assertNull(loggedInUser, "Login should fail with an incorrect password.");
    }

    @Test
    void testRegisterWithExistingUsername() {
        // 1. Arrange: Register a user
        UserDAO.register("Andi Wijaya", "andi", "password");

        // 2. Act: Attempt to register another user with the same username
        boolean isRegisteredAgain = UserDAO.register("Andi Baru", "andi", "new_password");

        // 3. Assert: The second registration should fail
        assertFalse(isRegisteredAgain, "Registration should fail if the username already exists.");
    }

    @Test
    void testUpdateUser() {
        // 1. Arrange: Register and log in a user
        UserDAO.register("Rina Lestari", "rina", "oldpass");
        User user = UserDAO.login("rina", "oldpass");
        assertNotNull(user);

        // 2. Act: Change the user's details and update them
        user.setName("Rina Hartono");
        user.setPassword("newpass");
        boolean isUpdated = UserDAO.updateUser(user);

        // 3. Assert: The update should be successful
        assertTrue(isUpdated, "User update operation should return true.");

        // Verify by logging in with the new credentials
        User updatedUser = UserDAO.login("rina", "newpass");
        assertNotNull(updatedUser, "Should be able to log in with the new password.");
        assertEquals("Rina Hartono", updatedUser.getName(), "User's name should be updated in the database.");
    }
}
