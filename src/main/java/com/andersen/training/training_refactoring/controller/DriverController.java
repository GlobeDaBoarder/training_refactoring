package com.andersen.training.training_refactoring.controller;

import com.andersen.training.training_refactoring.dto.request.DriverCreationDto;
import com.andersen.training.training_refactoring.dto.response.DriverResponseDto;
import com.andersen.training.training_refactoring.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping
    public ResponseEntity<DriverResponseDto> addDriver(@RequestBody DriverCreationDto driverCreationDto) {
        DriverResponseDto driverResponseDto = driverService.addDriver(driverCreationDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(driverResponseDto.id())
                .toUri();

        return ResponseEntity.created(location).body(driverResponseDto);
    }
}
