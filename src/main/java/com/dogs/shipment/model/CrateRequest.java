package com.dogs.shipment.model;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrateRequest {

    @NonNull
    String name;
}
