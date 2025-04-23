package com.dogs.shipment.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dogs.shipment.entity.Dog;

@SpringBootTest
class DogRepositoryTest {

	@Autowired
	private DogRepository dogRepository;

	@Test
	void contextLoads() {
	}

	@Test
    void testCreateDog() {
		// Create a new item
		Dog dog = Dog.builder().name("I am a dog").weight(20.0).build();

		// Save the item to the database
		dogRepository.save(dog);

		assertNotNull(dog.getDogId());
	}

}
