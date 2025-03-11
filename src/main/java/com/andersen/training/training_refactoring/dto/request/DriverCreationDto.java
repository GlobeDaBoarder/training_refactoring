package com.andersen.training.training_refactoring.dto.request;

import lombok.Builder;

@Builder
public record DriverCreationDto(
        String driverName,
        String carBrand
) {
}
