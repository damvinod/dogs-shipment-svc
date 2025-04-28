package com.dogs.shipment.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class CrateRequestTest {

    @Test
    @DisplayName("Should return error if name is null")
    void shouldFailIfNameIsNull() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        CrateRequest crateRequest = CrateRequest.builder().build();
        Set<ConstraintViolation<CrateRequest>> violations = validator.validate(crateRequest);

        List<String> errorFields = violations
                .stream()
                .map(ConstraintViolation::getPropertyPath)
                .map(Path::toString)
                .collect(toList());

        assertThat(errorFields).containsAnyElementsOf(Arrays.asList("name"));

        List<String> errorMessages = violations
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(toList());

        assertThat(errorMessages).size().isEqualTo(1);
    }

    @Test
    @DisplayName("Should not return error if all fields are given in the input")
    void shouldPassIftNameIsPresent() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        CrateRequest crateRequest = CrateRequest.builder().name("Dog Crate").build();

        Set<ConstraintViolation<CrateRequest>> violations = validator.validate(crateRequest);

        List<String> errorMessages = violations
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(toList());

        assertThat(errorMessages).size().isEqualTo(0);
    }
}
