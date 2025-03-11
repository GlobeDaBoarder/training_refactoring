package com.andersen.training.training_refactoring.mapper;

import com.andersen.training.training_refactoring.dto.request.DriverCreationDto;
import com.andersen.training.training_refactoring.dto.response.DriverResponseDto;
import com.andersen.training.training_refactoring.entity.Driver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    Driver toDriverEntity(DriverCreationDto driverCreationDto);
    DriverResponseDto toDriverResponseDto(Driver driver);
}
