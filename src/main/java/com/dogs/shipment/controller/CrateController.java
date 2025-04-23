package com.dogs.shipment.controller;

import com.dogs.shipment.entity.Crate;
import com.dogs.shipment.model.CrateRequest;
import com.dogs.shipment.service.CrateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crates")
public class CrateController {

    @Autowired
    private CrateService crateService;

    @PostMapping
    public ResponseEntity<Crate> createCrate(@RequestBody CrateRequest crateRequest) {
        Crate createdCrate = crateService.createCrate(crateRequest.getName());
        
        return ResponseEntity.ok((createdCrate));
    }
}
