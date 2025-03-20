package com.andersen.training.training_refactoring.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record RaceEventDto(
        LocalDate date,
        Long raceTrackId,
        Set<RaceResultDto> raceResultDtos
) {
}
