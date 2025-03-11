package com.andersen.training.training_refactoring.dto.request;

import lombok.Builder;

@Builder
public record RaceTrackCreationDto(
        String trackName,
        String country) {
}
