package com.andersen.training.training_refactoring.service;

import com.andersen.training.training_refactoring.dto.RaceEventDto;
import com.andersen.training.training_refactoring.entity.Driver;
import com.andersen.training.training_refactoring.entity.RaceEvent;
import com.andersen.training.training_refactoring.entity.RaceResult;
import com.andersen.training.training_refactoring.entity.RaceTrack;
import com.andersen.training.training_refactoring.exception.DriverNotFoundException;
import com.andersen.training.training_refactoring.exception.RaceTrackNotFoundException;
import com.andersen.training.training_refactoring.repo.DriverRepo;
import com.andersen.training.training_refactoring.repo.RaceEventRepo;
import com.andersen.training.training_refactoring.repo.RaceTrackRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RaceEventService {

    private final RaceEventRepo raceEventRepo;
    private final DriverRepo driverRepo;
    private final RaceTrackRepo raceTrackRepo;

    /// @throws DriverNotFoundException if driver with given id not found
    /// @throws RaceTrackNotFoundException if racetrack with given id not found
    public void addRaceEvent(RaceEventDto raceEventDto) {
        Set<RaceResult> raceResultEntities = new HashSet<>();
        raceEventDto.raceResultDtos().forEach(raceResultDto ->
                raceResultEntities.add(RaceResult.builder()
                        .driver(findDriver(raceResultDto.driverId()))
                        .finishingPosition(raceResultDto.finishingPosition()).build()));

        RaceEvent raceEventEntity = RaceEvent.builder()
                .date(raceEventDto.date())
                .raceTrack(findRaceTrack(raceEventDto.raceTrackId()))
                .raceResults(raceResultEntities)
                .build();

        raceEventRepo.save(raceEventEntity);
    }

    private Driver findDriver(Long driverId) {
        return driverRepo.findById(driverId)
                .orElseThrow(() -> new DriverNotFoundException(driverId));
    }

    private RaceTrack findRaceTrack(Long raceTrackId) {
        return raceTrackRepo.findById(raceTrackId)
                .orElseThrow(() -> new RaceTrackNotFoundException(raceTrackId));
    }
}
