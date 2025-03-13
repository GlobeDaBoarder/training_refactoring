package com.andersen.training.training_refactoring.dto;

import lombok.Builder;

@Builder
public record RaceResultCreationDto(
        Long driverId,
        Integer finishingPosition
) {
}
