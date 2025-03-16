package com.andersen.training.training_refactoring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RaceResult {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne
    private Driver driver;

    private Integer finishingPosition;

    @Builder
    private RaceResult(Driver driver, Integer finishingPosition) {
        this.driver = driver;
        this.finishingPosition = finishingPosition;
    }
}
