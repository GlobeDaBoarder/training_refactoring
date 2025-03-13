package com.andersen.training.training_refactoring.controller;

import com.andersen.training.training_refactoring.dto.RaceEventCreationDto;
import com.andersen.training.training_refactoring.service.RaceEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/race-event")
@RequiredArgsConstructor
public class RaceEventController {

    private final RaceEventService raceEventService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addRaceEvent(@RequestBody RaceEventCreationDto raceEventCreationDto) {
        raceEventService.addRaceEvent(raceEventCreationDto);
    }
}
