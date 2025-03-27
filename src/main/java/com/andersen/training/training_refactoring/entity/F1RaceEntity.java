package com.andersen.training.training_refactoring.entity;

import com.andersen.training.training_refactoring.controller.F1RaceController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/// ### Deprecated Entity in legacy F1 API
///
/// Legacy F1RaceEntity used in the F1 API({@link F1RaceController}) as persistence model
///
/// @deprecated Use {@link Driver}, {@link RaceResult}, {@link RaceEvent} and {@link RaceTrack} instead.
///
/// @author Gleb
/// @since 2.0.0
@Entity
@Table(name = "f1_races")
@Getter
@Setter
@Deprecated
public class F1RaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String country;
    private String track;
    private String driver;
    private String car;
    private int position;
}
