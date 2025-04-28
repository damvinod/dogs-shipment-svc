package com.dogs.shipment.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public final class CrateRequest {


    @NotEmpty(message = "Name is mandatory field")
    String name;

    public CrateRequest() {
        this.name = "";
    }
}
