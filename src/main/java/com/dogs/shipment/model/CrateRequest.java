package com.dogs.shipment.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class CrateRequest {

    @NotEmpty(message = "Name is mandatory field")
    private String name;
}
