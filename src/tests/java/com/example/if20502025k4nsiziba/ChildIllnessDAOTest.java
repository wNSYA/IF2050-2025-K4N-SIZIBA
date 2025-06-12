package com.example.if20502025k4nsiziba;

import com.example.if20502025k4nsiziba.database.DatabaseHelper;
import com.example.if20502025k4nsiziba.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

class ChildIllnessDAOTest {

    private ChildIllnessDAO illnessDAO;
    private ChildDAO childDAO;
    private User testUser;
    private Child testChild;

    @BeforeEach
    void setUp() throws SQLException {
        // Initialize the database and DAOs before each test
        DatabaseHelper.initializeDatabase();
        illnessDAO = new ChildIllnessDAO();
        childDAO = new ChildDAO();

        // Ensure the database is clean before each test run
        clearDatabaseTables();

        // Create a user and a child for test context
        UserDAO.register("testuser", "testuser", "password");
        testUser = UserDAO.login("testuser", "password");
        assertNotNull(testUser, "Test user should be created and logged in");

        testChild = new Child("Test Child", true, LocalDate.now().minusYears(1), 75.0f, 10.0f, 45.0f, 48.0f, 15.0f, testUser.getId());
        childDAO.insertChild(testChild);
        assertTrue(testChild.getId() > 0, "Test child should be inserted and have an ID");
    }

    @AfterEach
    void tearDown() throws SQLException {
        // Clean up the database after each test to ensure isolation
        clearDatabaseTables();
    }

    private void clearDatabaseTables() throws SQLException {
        try (Connection conn = DatabaseHelper.connect(); Statement stmt = conn.createStatement()) {
            // The order matters due to foreign key constraints. Delete from child tables first.
            stmt.execute("DELETE FROM child_illness");
            stmt.execute("DELETE FROM child");
            stmt.execute("DELETE FROM users");
            // Reset autoincrement counters
            stmt.execute("DELETE FROM sqlite_sequence WHERE name IN ('child_illness', 'child', 'users')");
        }
    }

    @Test
    void testInsertAndGetIllnessesByChildId() {
        // 1. Arrange
        ChildIllness newIllness = new ChildIllness(testChild.getId(), "Chickenpox", "Visible red spots.", LocalDate.of(2025, 5, 20));

        // 2. Act
        illnessDAO.insertIllness(newIllness);
        List<ChildIllness> illnesses = illnessDAO.getIllnessesByChildId(testChild.getId());

        // 3. Assert
        assertNotNull(illnesses);
        assertEquals(1, illnesses.size());
        assertEquals("Chickenpox", illnesses.get(0).getIllnessName());
    }

    @Test
    void testDeleteIllness() {
        // 1. Arrange
        ChildIllness newIllness = new ChildIllness(testChild.getId(), "Ear Infection", "Child pulling ear.", LocalDate.now());
        illnessDAO.insertIllness(newIllness);
        assertTrue(newIllness.getId() > 0);

        // 2. Act
        illnessDAO.deleteIllness(newIllness.getId());

        // 3. Assert
        List<ChildIllness> illnessesAfterDelete = illnessDAO.getIllnessesByChildId(testChild.getId());
        assertTrue(illnessesAfterDelete.isEmpty());
    }

    @Test
    void testGetIllnessesForChildWithNoHistory() {
        // Act
        List<ChildIllness> illnesses = illnessDAO.getIllnessesByChildId(testChild.getId());

        // Assert
        assertNotNull(illnesses);
        assertTrue(illnesses.isEmpty());
    }

    // --- NEW TESTS FOR BETTER COVERAGE ---

    @Test
    void testShouldReturnMultipleIllnessesForOneChild() {
        // 1. Arrange: Insert two different illnesses for the same child
        ChildIllness illness1 = new ChildIllness(testChild.getId(), "Cold", "Runny nose.", LocalDate.now().minusDays(10));
        ChildIllness illness2 = new ChildIllness(testChild.getId(), "Fever", "High temperature.", LocalDate.now().minusDays(5));
        illnessDAO.insertIllness(illness1);
        illnessDAO.insertIllness(illness2);

        // 2. Act: Retrieve the illnesses for that child
        List<ChildIllness> illnesses = illnessDAO.getIllnessesByChildId(testChild.getId());

        // 3. Assert: Check that both illnesses were returned
        assertNotNull(illnesses);
        assertEquals(2, illnesses.size(), "Should retrieve all illnesses for the child.");
    }

    @Test
    void testShouldNotGetIllnessesFromOtherChildren() {
        // 1. Arrange: Create a second child and add an illness only to them
        Child otherChild = new Child("Other Child", false, LocalDate.now(), 50, 5, 30, 30, 10, testUser.getId());
        childDAO.insertChild(otherChild);
        assertTrue(otherChild.getId() > 0);

        ChildIllness otherIllness = new ChildIllness(otherChild.getId(), "Rash", "Skin rash.", LocalDate.now());
        illnessDAO.insertIllness(otherIllness);

        // 2. Act: Get illnesses for the original test child
        List<ChildIllness> illnesses = illnessDAO.getIllnessesByChildId(testChild.getId());

        // 3. Assert: The list should be empty, as the illness belongs to someone else
        assertTrue(illnesses.isEmpty(), "Should not fetch illnesses belonging to a different child.");
    }
}
