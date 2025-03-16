package com.andersen.training.training_refactoring.service;

import com.andersen.training.training_refactoring.dto.DriverDto;
import com.andersen.training.training_refactoring.entity.Driver;
import com.andersen.training.training_refactoring.mapper.DriverMapperImpl;
import com.andersen.training.training_refactoring.repo.DriverRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DriverServiceTest {

    @Mock
    DriverRepo driverRepo;

    @Captor
    ArgumentCaptor<Driver> driverCaptor;

    DriverService driverService;

    @BeforeEach
    void setUp() {
        driverService = new DriverService(driverRepo, new DriverMapperImpl());
    }

    @Test
    void addDriver_withDriverDto_callsDriverRepo() {
        DriverDto driverDto = new DriverDto("Lewis Hamilton", "Mercedes");
        when(driverRepo.save(any()))
                .thenReturn(mock(Driver.class));

        driverService.addDriver(driverDto);

        verify(driverRepo).save(driverCaptor.capture());

        assertThat(driverCaptor.getValue())
                .returns(driverDto.driverName(), Driver::getDriverName)
                .returns(driverDto.carBrand(), Driver::getCarBrand);
    }
}
