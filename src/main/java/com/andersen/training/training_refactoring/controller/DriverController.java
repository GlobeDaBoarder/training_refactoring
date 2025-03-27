package com.andersen.training.training_refactoring.controller;

import com.andersen.training.training_refactoring.dto.DriverDto;
import com.andersen.training.training_refactoring.service.DriverService;
import com.andersen.training.training_refactoring.service.PredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/driver")
@RequiredArgsConstructor
public class DriverController implements DriverApi {

    private final DriverService driverService;
    private final PredictionService predictionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addDriver(@Validated @RequestBody DriverDto driverDto) {
        driverService.addDriver(driverDto);
    }

    @GetMapping("/{driverId}/winning-chance")
    @ResponseStatus(HttpStatus.OK)
    public String predictDriverWinningChance(@PathVariable Long driverId) {
        return predictionService.predictWinningChance(driverId);
    }
}
