package com.andersen.training.training_refactoring.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

@Builder
public record RaceResultDto(

        @PositiveOrZero
        Long driverId,

        @Positive
        Integer finishingPosition
) {
}
