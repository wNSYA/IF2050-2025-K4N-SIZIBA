package com.example.if20502025k4nsiziba;

import com.example.if20502025k4nsiziba.model.ChildIllness;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ChildIllnessTest {

    @Test
    void testChildIllnessCreationAndGetters() {
        // 1. Arrange: Define test data
        int childId = 1;
        String illnessName = "Fever";
        String description = "High temperature, given paracetamol.";
        LocalDate dateOfIllness = LocalDate.of(2025, 6, 12);

        // 2. Act: Create a new ChildIllness object
        ChildIllness illness = new ChildIllness(childId, illnessName, description, dateOfIllness);
        illness.setId(101); // Simulate setting an ID after DB insertion

        // 3. Assert: Verify that all getters return the correct values
        assertNotNull(illness);
        assertEquals(101, illness.getId());
        assertEquals(childId, illness.getChildId());
        assertEquals(illnessName, illness.getIllnessName());
        assertEquals(description, illness.getDescription());
        assertEquals(dateOfIllness, illness.getDateOfIllness());
    }
}