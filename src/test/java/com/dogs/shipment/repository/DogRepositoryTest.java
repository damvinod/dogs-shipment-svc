package com.dogs.shipment.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void shouldSaveWhenDogIsCreated() {
		// Create a new item
		Dog dog = Dog.builder().name("I am a dog").weight(20.0).build();

		// Save the item to the database
		dogRepository.save(dog);

		assertNotNull(dog.getDogId());
	}

	@Test
    void getDogByIdShouldReturnAttributes() {
		// Create a new item
		Dog dog = Dog.builder().name("I am a dog").weight(20.0).build();

		// Save the item to the database
		dogRepository.save(dog);

		Dog retrievedDog = dogRepository.findById(dog.getDogId()).orElse(null);

		assertEquals(dog.getDogId(), retrievedDog.getDogId());
		assertEquals(dog.getName(), retrievedDog.getName());
		assertEquals(dog.getWeight(), retrievedDog.getWeight());
	}

	@Test
    void shouldThrowExceptionWhenIdIsUpdated() {
		// Create a new item
		Dog dog = Dog.builder().name("I am a dog").weight(20.0).build();

		// Save the item to the database
		dogRepository.save(dog);

		// Update the ID
		dog.setDogId(1234L);

		// Assert that saving with an updated ID throws an exception
		assertThrows(Exception.class, () -> dogRepository.save(dog));
	}

}
