package com.andersen.training.training_refactoring.dto.response;

import lombok.Builder;

@Builder
public record DriverResponseDto(
        Long id,
        String driverName,
        String carBrand
) {
}
