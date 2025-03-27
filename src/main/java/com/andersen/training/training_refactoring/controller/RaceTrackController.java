package com.andersen.training.training_refactoring.controller;

import com.andersen.training.training_refactoring.dto.RaceTrackDto;
import com.andersen.training.training_refactoring.service.RaceTrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/race-track")
@RequiredArgsConstructor
public class RaceTrackController implements RaceTrackApi{

    private final RaceTrackService raceTrackService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addRaceTrack(@Validated @RequestBody RaceTrackDto raceTrackDto) {
        raceTrackService.addRaceTrack(raceTrackDto);
    }
}
