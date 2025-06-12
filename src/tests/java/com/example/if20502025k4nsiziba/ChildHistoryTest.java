package com.example.if20502025k4nsiziba;

import com.example.if20502025k4nsiziba.model.Child;
import com.example.if20502025k4nsiziba.model.ChildHistory;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ChildHistoryTest {

    @Test
    void testChildHistoryCreationFromChild() {
        // 1. Arrange: Create a parent Child object with specific data
        int childId = 55;
        int userId = 10;
        Child child = new Child(
                "Anak Sejarah",
                true, // gender: Male
                LocalDate.of(2023, 10, 1), // birthDate
                80.5f, // height
                11.2f, // weight
                46.1f, // headCircumference
                50.3f, // abdominalCircumference
                16.5f, // handCircumference
                userId
        );
        child.setId(childId); // Simulate the ID that would be assigned by the database

        // 2. Act: Create a ChildHistory object from the Child object
        ChildHistory historyEntry = new ChildHistory(child);

        // 3. Assert: Verify that all data was copied correctly
        assertNotNull(historyEntry);
        assertEquals(childId, historyEntry.getChildId());
        assertEquals(child.getHeight(), historyEntry.getHeight());
        assertEquals(child.getWeight(), historyEntry.getWeight());
        assertEquals(child.getHeadCircumference(), historyEntry.getHeadCircumference());
        assertEquals(child.getAbdominalCircumference(), historyEntry.getAbdominalCircumference());
        assertEquals(child.getHandCircumference(), historyEntry.getHandCircumference());
        assertEquals(child.getDateAdded(), historyEntry.getDateAdded());

        // Also, verify that the recording date was set automatically
        assertNotNull(historyEntry.getDateRecorded(), "The dateRecorded field should be set upon creation.");
    }
}
