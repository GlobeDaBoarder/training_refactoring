package com.andersen.training.training_refactoring.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record RaceEventDto(

        @PastOrPresent
        LocalDate date,

        @PositiveOrZero
        Long raceTrackId,

        @NotEmpty
        Set<RaceResultDto> raceResultDtos
) {
}
