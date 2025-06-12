package com.example.if20502025k4nsiziba;

import com.example.if20502025k4nsiziba.model.Child;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ChildTest {

    @Test
    void testChildCreationAndGetters() {
        // 1. Arrange
        int userId = 1;
        LocalDate birthDate = LocalDate.of(2023, 1, 1);
        Child child = new Child("Bayi Sehat", true, birthDate, 75.0f, 10.0f, 45.0f, 48.0f, 15.0f, userId);
        child.setId(101); // Simulate setting ID after DB insertion

        // 2. Assert
        assertEquals(101, child.getId());
        assertEquals("Bayi Sehat", child.getName());
        assertTrue(child.isGender()); // true for Male
        assertEquals(birthDate, child.getBirthDate());
        assertEquals(75.0f, child.getHeight());
        assertEquals(10.0f, child.getWeight());
        assertEquals(45.0f, child.getHeadCircumference());
        assertEquals(48.0f, child.getAbdominalCircumference());
        assertEquals(15.0f, child.getHandCircumference());
        assertEquals(userId, child.getUserId());
        assertNotNull(child.getDateAdded()); // Should be set automatically
    }

    @Test
    void testGetAgeCalculationInMonths() {
        // Test case 1: A child who is exactly 1 year and 2 months old
        LocalDate birthDate1 = LocalDate.now().minusYears(1).minusMonths(2);
        Child child1 = new Child("Child One", true, birthDate1, 75, 10, 45, 48, 15, 1);
        // Expected age: (1 * 12) + 2 = 14 months
        assertEquals(14, child1.getAge(), "Age should be calculated correctly in total months.");

        // Test case 2: A child who is less than a month old
        LocalDate birthDate2 = LocalDate.now().minusDays(15);
        Child child2 = new Child("Newborn", false, birthDate2, 50, 3, 30, 32, 9, 1);
        // Expected age: 0 months
        assertEquals(0, child2.getAge(), "Age should be 0 for a newborn.");

        // Test case 3: A child born today
        LocalDate birthDate3 = LocalDate.now();
        Child child3 = new Child("Just Born", true, birthDate3, 50, 3, 30, 32, 9, 1);
        assertEquals(0, child3.getAge(), "Age should be 0 for a child born today.");
    }
}
