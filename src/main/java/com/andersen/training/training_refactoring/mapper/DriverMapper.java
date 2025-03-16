package com.andersen.training.training_refactoring.mapper;

import com.andersen.training.training_refactoring.dto.DriverDto;
import com.andersen.training.training_refactoring.entity.Driver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    Driver toDriverEntity(DriverDto driverDto);
}
