package com.dogs.shipment.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dogs.shipment.entity.Crate;
import com.dogs.shipment.service.CrateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CrateControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CrateService crateService;

    @InjectMocks
    private CrateController crateController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(crateController).build();
    }

    @Test
    public void shouldCreateCrateWhenControllerMethodIsInvoked() throws Exception {
        // Arrange
        String requestBody = "{\"name\":\"Dog Crate\"}";
        String responseBody = "{\"crateId\":1,\"name\":\"Dog Crate\",\"status\":\"Available\"}";

        Crate crate = Crate.builder()
                .crateId(1L)
                .name("Dog Crate")
                .status("Available")
                .build();

        when(crateService.createCrate(anyString())).thenReturn(crate);

        // Act & Assert
        mockMvc.perform(post("/crates")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }
}
