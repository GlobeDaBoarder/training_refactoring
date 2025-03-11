package com.andersen.training.training_refactoring.dto.request;

import lombok.Builder;

@Builder
public record RaceResultCreationDto(
        Long driverId,
        Integer finishingPosition
) {
}
