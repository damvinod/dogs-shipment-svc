package com.dogs.shipment.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.dogs.shipment.repository.CrateRepository;
import com.dogs.shipment.entity.Crate;

@Service
public class CrateService {

    @Autowired
    private CrateRepository crateRepository;

    public Crate createCrate(String name) {
        Crate crate = Crate.builder()
        .name(name)
        .status("Available")
        .build();
        crateRepository.save(crate);
        return crate;
    }
}
