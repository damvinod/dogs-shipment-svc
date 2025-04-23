package com.dogs.shipment.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dogs.shipment.entity.Crate;

@SpringBootTest
class CrateRepositoryTest {

	@Autowired
	private CrateRepository crateRepository;

	@Test
	void contextLoads() {
	}

	@Test
    void shouldSaveWhenCrateIsCreated() {
		// Create a new item
		Crate crate = Crate.builder().name("I am a crate").status("Available").build();

		// Save the item to the database
		crateRepository.save(crate);

		assertNotNull(crate.getCrateId());
	}

	@Test
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
