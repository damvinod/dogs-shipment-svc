package com.dogs.shipment.controller;

import com.dogs.shipment.model.CrateRequest;
import com.dogs.shipment.model.CrateResponse;
import com.dogs.shipment.service.CrateService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crates")
@Slf4j
public class CrateController {

    private CrateService crateService;

    CrateController(CrateService crateService) {
        this.crateService = crateService;
    }

    @PostMapping
    public ResponseEntity<CrateResponse> createCrate(@Valid @RequestBody CrateRequest crateRequest) {
        log.info("Create controller invoked for creating crate.");
        Long crateId = crateService.createCrate(crateRequest.getName());
        return ResponseEntity.ok((CrateResponse.builder().id(crateId).build()));
    }
}
