package com.dogs.shipment.controller;

import com.dogs.shipment.model.CrateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CrateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @TestInstance(PER_CLASS)
    @Nested
    @DisplayName("This class is used for getting the schedule of Gym class")
    class CreateCrate {

        @Test
        @DisplayName("Creating the crate.")
        public void shouldCreateCrate() throws Exception {

            CrateRequest crateRequest = CrateRequest.builder().name("Test 1").build();

            mockMvc.perform(post("/crates")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(crateRequest)))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber());
        }

        @Test
        @DisplayName("Testing Bad request without giving the name.")
        public void shouldGetBadRequestError() throws Exception {

            CrateRequest crateRequest = CrateRequest.builder().build();

            mockMvc.perform(post("/crates")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(crateRequest)))
                    .andExpect(status().isBadRequest());
        }
    }
}
