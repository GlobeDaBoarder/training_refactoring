package com.andersen.training.training_refactoring.dto;

import java.time.LocalDate;
import java.util.Set;

public record RaceEventDto(
        LocalDate date,
        Long raceTrackId,
        Set<RaceResultDto> raceResultDtos
) {
}
