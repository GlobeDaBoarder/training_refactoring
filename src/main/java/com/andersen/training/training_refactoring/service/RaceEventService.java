package com.andersen.training.training_refactoring.service;

import com.andersen.training.training_refactoring.dto.RaceEventCreationDto;
import com.andersen.training.training_refactoring.entity.RaceEvent;
import com.andersen.training.training_refactoring.entity.RaceResult;
import com.andersen.training.training_refactoring.repo.DriverRepo;
import com.andersen.training.training_refactoring.repo.RaceEventRepo;
import com.andersen.training.training_refactoring.repo.RaceTrackRepo;
import jakarta.transaction.Transactional;
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

    @Transactional
    public void addRaceEvent(RaceEventCreationDto raceEventCreationDto) {
        Set<RaceResult> raceResultEntities = new HashSet<>();
        raceEventCreationDto.raceResultCreationDtos().forEach(raceResultCreationDto ->
                raceResultEntities.add(RaceResult.builder()
                        .driver(driverRepo.getReferenceById(raceResultCreationDto.driverId()))
                        .finishingPosition(raceResultCreationDto.finishingPosition()).build()));

        RaceEvent raceEventEntity = RaceEvent.builder()
                .date(raceEventCreationDto.date())
                .raceTrack(raceTrackRepo.getReferenceById(raceEventCreationDto.raceTrackId()))
                .raceResults(raceResultEntities)
                .build();

        raceEventRepo.save(raceEventEntity);
    }
}
