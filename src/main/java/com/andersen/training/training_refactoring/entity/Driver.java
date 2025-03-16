package com.andersen.training.training_refactoring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Driver {

    @Builder
    public Driver(String driverName, String carBrand) {
        this.driverName = driverName;
        this.carBrand = carBrand;
    }

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    private String driverName;

    private String carBrand;
}
