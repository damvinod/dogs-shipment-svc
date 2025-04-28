package com.dogs.shipment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CrateStatus {

    NOT_SHIPPED("Not Shipped"),
    SHIPPED("Shipped");

    private final String status;
}
