package com.dogs.shipment.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateDogRequest {

    @NotEmpty(message = "Dog name is required")
    private String dogName;

    @NotEmpty(message = "Weight is required")
    private double weight;

    @NotEmpty(message = "Dog intelligence is required")
    private Intelligence intelligence;

    @NotEmpty(message = "Dog Shedding is required")
    private Shedding shedding;

    @NotEmpty(message = "Dog crate id is required")
    private String crateId;
}
