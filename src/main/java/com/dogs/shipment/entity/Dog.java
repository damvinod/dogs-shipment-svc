package com.dogs.shipment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Table(name = "dog")
@Entity(name = "dog")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOG_ID ")
    private Long dogId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "WEIGHT")
    private double weight;

    @Column(name = "INTELLIGENCE")
    private String intelligence;

    @Column(name = "SHEDDING")
    private String shedding;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CRATE_ID")
    private Crate crate;

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
