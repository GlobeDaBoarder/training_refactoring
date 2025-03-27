package com.andersen.training.training_refactoring.dto;

import com.andersen.training.training_refactoring.controller.F1RaceController;

import java.time.LocalDate;
import java.util.List;

/// ### Deprecated DTO in legacy F1 API
///
/// Legacy F1RaceResultsDto used in the F1 API({@link F1RaceController}) as request DTO
///
/// @deprecated Use {@link DriverDto}, {@link RaceResultDto}, {@link RaceEventDto} and {@link RaceTrackDto} instead.
///
/// @author Gleb
/// @since 2.0.0
@Deprecated
public record F1RaceResultsDto(
        LocalDate date,
        String country,
        String track,
        List<DriverResultDto> results
) {
}
