package com.andersen.training.training_refactoring.dto;

import lombok.Builder;

@Builder
public record RaceResultDto(
        Long driverId,
        Integer finishingPosition
) {
}
