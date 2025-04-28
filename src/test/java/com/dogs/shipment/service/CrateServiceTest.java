package com.dogs.shipment.service;

import com.dogs.shipment.entity.Crate;
import com.dogs.shipment.repository.CrateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrateServiceTest {

    @Mock
    private CrateRepository crateRepository;

    private CrateService crateService;

    @BeforeEach
    void setUp() {
        crateService = new CrateService(crateRepository);
    }

    @Test
    @DisplayName("Creating the crate.")
    void shouldBeAbleToCreateACrate() {
        String crateName = "Test Crate";

        when(crateRepository.save(any(Crate.class)))
                .thenReturn(Crate.builder().name(crateName).build());

        //Crate createdCrate = crateService.createCrate(crateName);
        crateService.createCrate(crateName);

        //assertEquals(createdCrate.getName(), crateName);
    }
}
