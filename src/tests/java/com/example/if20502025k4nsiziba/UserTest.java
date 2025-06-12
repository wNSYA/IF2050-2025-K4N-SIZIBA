package com.example.if20502025k4nsiziba;

import com.example.if20502025k4nsiziba.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserCreationAndGetters() {
        // 1. Arrange: Define user data
        int id = 1;
        String name = "Joko Widodo";
        String username = "jokowi";
        String password = "securepassword";

        // 2. Act: Create a User object
        User user = new User(id, name, username, password);

        // 3. Assert: Verify that all getters return the correct initial values
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
    }

    @Test
    void testUserSetters() {
        // 1. Arrange: Create a user with initial data
        User user = new User(10, "Initial Name", "initial_user", "initial_pass");

        // 2. Act: Use setters to change the user's properties
        user.setName("Updated Name");
        user.setUsername("updated_user");
        user.setPassword("updated_pass");

        // 3. Assert: Verify that the getters now return the updated values
        assertEquals("Updated Name", user.getName());
        assertEquals("updated_user", user.getUsername());
        assertEquals("updated_pass", user.getPassword());
    }
}
