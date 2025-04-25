package com.dogs.shipment.model;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public final class CrateRequest {

    @NonNull
    String name;

    public CrateRequest() {
        this.name = "";
    }
}
