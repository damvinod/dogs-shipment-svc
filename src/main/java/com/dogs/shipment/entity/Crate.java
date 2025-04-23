package com.dogs.shipment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Table(name = "crate")
@Entity(name = "crate")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Crate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CRATE_ID")
    private Long crateId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATED_DATE")
    @CreationTimestamp
    private Instant createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "LAST_UPDATE_DATE")
    @UpdateTimestamp
    private Instant lastUpdateDate;

    @Column(name = "LAST_UPDATE_BY")
    private String lastUpdateBy;
}