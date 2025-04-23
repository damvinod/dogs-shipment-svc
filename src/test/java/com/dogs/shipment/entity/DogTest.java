package com.dogs.shipment.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DogTest {

    @Test
    void testDogBuilderAndGetters() {
        Dog dog = Dog.builder()
                .dogId(1L)
                .name("Buddy")
                .weight(25.5)
                .intelligence("High")
                .shedding("Moderate")
                .build();

        assertEquals(1L, dog.getDogId());
        assertEquals("Buddy", dog.getName());
        assertEquals(25.5, dog.getWeight());
        assertEquals("High", dog.getIntelligence());
        assertEquals("Moderate", dog.getShedding());
    }

    @Test
    void testSetters() {
        Dog dog = new Dog();
        dog.setDogId(2L);
        dog.setName("Max");
        dog.setWeight(30.0);
        dog.setIntelligence("Medium");
        dog.setShedding("Low");

        assertEquals(2L, dog.getDogId());
        assertEquals("Max", dog.getName());
        assertEquals(30.0, dog.getWeight());
        assertEquals("Medium", dog.getIntelligence());
        assertEquals("Low", dog.getShedding());
    }
}
