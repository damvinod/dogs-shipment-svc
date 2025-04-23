package com.dogs.shipment.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dogs.shipment.entity.Crate;

@SpringBootTest
@DisplayName("Crate Repository Test")
class CrateRepositoryTest {

	@Autowired
	private CrateRepository crateRepository;

	@Test
	@DisplayName("Saving the crate should return the crate with ID")
    void shouldSaveWhenCrateIsCreated() {
		// Create a new item
		Crate crate = Crate.builder().name("I am a crate").status("Available").build();

		// Save the item to the database
		crateRepository.save(crate);

		assertNotNull(crate.getCrateId());
	}

	@Test
	@DisplayName("Getting the crate by ID should return the correct attributes")
    void getCrateByIdShouldReturnAttributes() {
		// Create a new crate
		Crate crate = Crate.builder().name("I am a crate").status("Available").build();

		// Save the crate to the database
		crateRepository.save(crate);

		Crate retrievedCrate = crateRepository.findById(crate.getCrateId()).orElse(null);

		assertEquals(crate.getCrateId(), retrievedCrate.getCrateId());
		assertEquals(crate.getName(), retrievedCrate.getName());
		assertEquals(crate.getStatus(), retrievedCrate.getStatus());
	}

	@Test
	@DisplayName("Should throw exception when ID is updated")
    void shouldThrowExceptionWhenIdIsUpdated() {
		// Create a new crate
		Crate crate = Crate.builder().name("I am a crate").status("Available").build();

		// Save the crate to the database
		crateRepository.save(crate);

		// Update the ID
		crate.setCrateId(1234L);

		// Assert that saving with an updated ID throws an exception
		assertThrows(Exception.class, () -> crateRepository.save(crate));
	}

}
