package com.andersen.training.training_refactoring.dto.request;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record RaceEventCreationDto(
        LocalDate date,
        Long raceTrackId,
        Set<RaceResultCreationDto> raceResultCreationDtos
) {
}
