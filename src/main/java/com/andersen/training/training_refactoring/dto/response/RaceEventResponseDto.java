package com.andersen.training.training_refactoring.dto.response;

import com.andersen.training.training_refactoring.dto.request.RaceResultCreationDto;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record RaceEventResponseDto(
        Long id,
        LocalDate date,
        Long raceTrackId,
        Set<Long> raceResultIds
) {
}
