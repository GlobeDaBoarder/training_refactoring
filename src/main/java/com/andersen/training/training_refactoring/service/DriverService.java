package com.andersen.training.training_refactoring.service;

import com.andersen.training.training_refactoring.dto.DriverCreationDto;
import com.andersen.training.training_refactoring.entity.Driver;
import com.andersen.training.training_refactoring.mapper.DriverMapper;
import com.andersen.training.training_refactoring.repo.DriverRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepo driverRepo;
    private final DriverMapper driverMapper;

    public void addDriver(DriverCreationDto driverCreationDto) {
        Driver driverEntity = driverMapper.toDriverEntity(driverCreationDto);
        driverRepo.save(driverEntity);
    }

}
