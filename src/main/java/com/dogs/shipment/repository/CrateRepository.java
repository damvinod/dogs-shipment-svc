package com.dogs.shipment.repository;

import com.dogs.shipment.entity.Crate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrateRepository extends JpaRepository<Crate, Long> {
}
