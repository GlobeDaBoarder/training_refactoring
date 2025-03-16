package com.andersen.training.training_refactoring.dto;

import java.time.LocalDate;
import java.util.List;

@Deprecated
public record F1RaceResultsDto(
        LocalDate date,
        String country,
        String track,
        List<DriverResultDto> results
) {
}
