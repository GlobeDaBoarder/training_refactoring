package com.andersen.training.training_refactoring.mapper;

import com.andersen.training.training_refactoring.dto.DriverCreationDto;
import com.andersen.training.training_refactoring.entity.Driver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    Driver toDriverEntity(DriverCreationDto driverCreationDto);
}
