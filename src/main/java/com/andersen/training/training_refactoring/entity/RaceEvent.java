package com.andersen.training.training_refactoring.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RaceEvent {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    private LocalDate date;

    @ManyToOne
    private RaceTrack raceTrack;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "race_event_id")
    private Set<RaceResult> raceResults;

    @Builder
    private RaceEvent(LocalDate date, RaceTrack raceTrack, Set<RaceResult> raceResults) {
        this.date = date;
        this.raceTrack = raceTrack;
        this.raceResults = raceResults;
    }
}
