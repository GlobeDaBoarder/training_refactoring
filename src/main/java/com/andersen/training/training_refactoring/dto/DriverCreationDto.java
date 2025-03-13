package com.andersen.training.training_refactoring.dto;

import lombok.Builder;

@Builder
public record DriverCreationDto(
        String driverName,
        String carBrand
) {
}
