package com.dogs.shipment.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CrateRequestTest {

    @Test
    void shouldFailIfNameIsNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            CrateRequest.builder().name(null).build();
        });
        assertEquals("name is marked non-null but is null", exception.getMessage());
    }

    @Test
    void shouldPassIftNameIsPresent() {
        CrateRequest crateRequest = CrateRequest.builder().name("Dog Crate").build();
        assertNotNull(crateRequest);
        assertEquals("Dog Crate", crateRequest.getName());
    }
}
