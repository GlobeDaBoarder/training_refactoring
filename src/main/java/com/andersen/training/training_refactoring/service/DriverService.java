package com.andersen.training.training_refactoring.service;

import com.andersen.training.training_refactoring.dto.DriverDto;
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

    public void addDriver(DriverDto driverDto) {
        Driver driverEntity = driverMapper.toDriverEntity(driverDto);
        driverRepo.save(driverEntity);
    }

}
