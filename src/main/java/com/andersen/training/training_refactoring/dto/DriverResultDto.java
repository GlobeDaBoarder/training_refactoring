package com.andersen.training.training_refactoring.dto;

/// ### Deprecated DTO in legacy F1 API
///
/// @deprecated Use {@link DriverDto}, {@link RaceResultDto}, {@link RaceEventDto} and {@link RaceTrackDto} instead.
/// @see F1RaceResultsDto
///
/// @author Gleb
/// @since 2.0.0
@Deprecated()
public record DriverResultDto(
        String driver,
        String car,
        int position
) {
}
