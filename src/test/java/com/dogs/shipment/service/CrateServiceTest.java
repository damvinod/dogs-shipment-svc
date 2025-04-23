package com.dogs.shipment.service;

import com.dogs.shipment.entity.Crate;
import com.dogs.shipment.repository.CrateRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CrateServiceTest {

    @Mock
    private CrateRepository crateRepository;

    @InjectMocks
    private CrateService crateService;

    public CrateServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldBeAbleToCreateACrate() {
        String crateName = "Test Crate";

        Crate createdCrate = crateService.createCrate(crateName);

        verify(crateRepository, times(1)).save(argThat(crate ->
            crate.getName().equals(crateName) && crate.getStatus().equals("Available")
        ));

        assertEquals(createdCrate.getName(), crateName);
    }
}
