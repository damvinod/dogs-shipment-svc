package com.dogs.shipment.controller;

import com.dogs.shipment.entity.Crate;
import com.dogs.shipment.model.CrateRequest;
import com.dogs.shipment.service.CrateService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crates")
public class CrateController {

    private CrateService crateService;

    CrateController(CrateService crateService) {
        this.crateService = crateService;
    }

    @PostMapping
    public ResponseEntity<Crate> createCrate(@Valid @RequestBody CrateRequest crateRequest) {
        Crate createdCrate = crateService.createCrate(crateRequest.getName());

        return ResponseEntity.ok((createdCrate));
    }
}
