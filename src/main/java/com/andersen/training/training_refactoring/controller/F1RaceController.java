package com.andersen.training.training_refactoring.controller;

import com.andersen.training.training_refactoring.dto.F1RaceResultsDto;
import com.andersen.training.training_refactoring.service.F1RaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Legacy API controller, responsible for creation of race events, tracks, drivers and their result among events in single request.
 *
 * @deprecated in favour of {@code {@link DriverController}}, {@code {@link RaceEventController} and {@code {@link RaceTrackController}}.
 * @since 2.0.0
 */
@RestController
@RequestMapping("/races")
@RequiredArgsConstructor
@Deprecated
public class F1RaceController implements F1RaceApi {

    private final F1RaceService raceService;

    @PostMapping("/save")
    public ResponseEntity<Void> saveRaceResults(@RequestBody List<F1RaceResultsDto> races) {
        raceService.saveRaces(races);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/chance/{driver}")
    public ResponseEntity<Double> getWinningChance(@PathVariable String driver) {
        Double winningChance = raceService.calculateWinningChanceForDriver(driver);

        return ResponseEntity.ok(winningChance);
    }
}

