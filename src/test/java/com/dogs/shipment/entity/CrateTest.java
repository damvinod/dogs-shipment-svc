package com.dogs.shipment.entity;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class CrateTest {

    @Test
    void testCrateBuilder() {
        Instant now = Instant.now();
        Crate crate = Crate.builder()
                .crateId(1L)
                .name("Test Crate")
                .status("Available")
                .createdDate(now)
                .createdBy("User1")
                .lastUpdateDate(now)
                .lastUpdateBy("User2")
                .build();

        assertNotNull(crate);
        assertEquals(1L, crate.getCrateId());
        assertEquals("Test Crate", crate.getName());
        assertEquals("Available", crate.getStatus());
        assertEquals(now, crate.getCreatedDate());
        assertEquals("User1", crate.getCreatedBy());
        assertEquals(now, crate.getLastUpdateDate());
        assertEquals("User2", crate.getLastUpdateBy());
    }

    @Test
    void testDefaultConstructorAndSetters() {
        Crate crate = new Crate();
        crate.setCrateId(2L);
        crate.setName("Another Crate");
        crate.setStatus("In Use");
        crate.setCreatedDate(Instant.now());
        crate.setCreatedBy("User3");
        crate.setLastUpdateDate(Instant.now());
        crate.setLastUpdateBy("User4");

        assertEquals(2L, crate.getCrateId());
        assertEquals("Another Crate", crate.getName());
        assertEquals("In Use", crate.getStatus());
        assertNotNull(crate.getCreatedDate());
        assertEquals("User3", crate.getCreatedBy());
        assertNotNull(crate.getLastUpdateDate());
        assertEquals("User4", crate.getLastUpdateBy());
    }
}
