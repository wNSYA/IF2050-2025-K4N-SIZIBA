package com.example.if20502025k4nsiziba;

import com.example.if20502025k4nsiziba.database.DatabaseHelper;
import com.example.if20502025k4nsiziba.model.Child;
import com.example.if20502025k4nsiziba.model.ChildDAO;
import com.example.if20502025k4nsiziba.model.User;
import com.example.if20502025k4nsiziba.model.UserDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChildDAOTest {

    private ChildDAO childDAO;
    private User testUser1;
    private User testUser2;

    @BeforeEach
    void setUp() throws SQLException {
        DatabaseHelper.initializeDatabase();
        childDAO = new ChildDAO();
        clearDatabaseTables();

        // Create two distinct users for testing data isolation
        UserDAO.register("User One", "user1", "pass1");
        testUser1 = UserDAO.login("user1", "pass1");
        assertNotNull(testUser1);

        UserDAO.register("User Two", "user2", "pass2");
        testUser2 = UserDAO.login("user2", "pass2");
        assertNotNull(testUser2);
    }

    @AfterEach
    void tearDown() throws SQLException {
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
    void testInsertAndGetChildById() {
        // 1. Arrange
        Child newChild = new Child("Anak Pertama", true, LocalDate.of(2024, 1, 1), 50, 4, 35, 38, 10, testUser1.getId());

        // 2. Act
        childDAO.insertChild(newChild);
        assertTrue(newChild.getId() > 0, "Child should have a valid ID after insertion.");

        Child retrievedChild = childDAO.getChildById(newChild.getId());

        // 3. Assert
        assertNotNull(retrievedChild);
        assertEquals("Anak Pertama", retrievedChild.getName());
        assertEquals(testUser1.getId(), retrievedChild.getUserId());
    }

    @Test
    void testGetAllChildrenByUser() {
        // 1. Arrange: Add two children for user1 and one for user2
        childDAO.insertChild(new Child("Anak A", true, LocalDate.now(), 50, 4, 30, 30, 10, testUser1.getId()));
        childDAO.insertChild(new Child("Anak B", false, LocalDate.now(), 55, 5, 32, 32, 11, testUser1.getId()));
        childDAO.insertChild(new Child("Anak C", true, LocalDate.now(), 60, 6, 34, 34, 12, testUser2.getId()));

        // 2. Act: Get all children for user1
        List<Child> user1Children = childDAO.getAllChildrenByUser(testUser1.getId());

        // 3. Assert: Ensure only user1's children are returned
        assertNotNull(user1Children);
        assertEquals(2, user1Children.size(), "Should only retrieve children belonging to the specified user.");
    }

    @Test
    void testDeleteChild() {
        // 1. Arrange
        Child child = new Child("ToDelete", false, LocalDate.now(), 50, 5, 30, 30, 10, testUser1.getId());
        childDAO.insertChild(child);
        assertTrue(child.getId() > 0);

        // 2. Act
        childDAO.deleteChild(child.getId());

        // 3. Assert
        Child deletedChild = childDAO.getChildById(child.getId());
        assertNull(deletedChild, "Child should not be found in the database after deletion.");
    }
}
