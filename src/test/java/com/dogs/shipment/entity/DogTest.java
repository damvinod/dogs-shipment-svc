package com.dogs.shipment.entity;

import com.dogs.shipment.model.Intelligence;
import com.dogs.shipment.model.Shedding;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DogTest {

    @Test
    void testDogBuilderAndGetters() {
        Dog dog = Dog.builder()
                .dogId(1L)
                .name("Buddy")
                .weight(25.5)
                .intelligence(Intelligence.HIGH)
                .shedding(Shedding.LOW)
                .build();

        assertEquals(1L, dog.getDogId());
        assertEquals("Buddy", dog.getName());
        assertEquals(25.5, dog.getWeight());
        assertEquals(Intelligence.HIGH.name(), dog.getIntelligence().name());
        assertEquals(Shedding.LOW.name(), dog.getShedding().name());
    }

    @Test
    void testSetters() {
        Dog dog = new Dog();
        dog.setDogId(2L);
        dog.setName("Max");
        dog.setWeight(30.0);
        dog.setIntelligence(Intelligence.HIGH);
        dog.setShedding(Shedding.LOW);

        assertEquals(2L, dog.getDogId());
        assertEquals("Max", dog.getName());
        assertEquals(30.0, dog.getWeight());
        assertEquals(Intelligence.HIGH.name(), dog.getIntelligence().name());
        assertEquals(Shedding.LOW.name(), dog.getShedding().name());
    }
}
