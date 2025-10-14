package com.dogs.shipment.service;

import com.dogs.shipment.entity.Crate;
import com.dogs.shipment.repository.CrateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.dogs.shipment.model.CrateStatus.NOT_SHIPPED;

@Service
@Slf4j
public class CrateService {

    private CrateRepository crateRepository;

    CrateService(CrateRepository crateRepository) {
        this.crateRepository = crateRepository;
    }

    public Long createCrate(String name) {
        log.info("Create service invoked for creating crate.");
        Crate crate = Crate.builder()
                .name(name)
                .status(NOT_SHIPPED.getStatus())
                .build();

        crate = crateRepository.save(crate);

        return crate.getCrateId();
    }
}
