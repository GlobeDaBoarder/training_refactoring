package com.andersen.training.training_refactoring.service;

import com.andersen.training.training_refactoring.dto.RaceTrackDto;
import com.andersen.training.training_refactoring.entity.RaceTrack;
import com.andersen.training.training_refactoring.mapper.RaceTrackMapper;
import com.andersen.training.training_refactoring.repo.RaceTrackRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RaceTrackService {

    private final RaceTrackRepo raceTrackRepo;
    private final RaceTrackMapper raceTrackMapper;

    public void addRaceTrack(RaceTrackDto raceTrackDto) {
        RaceTrack driverEntity = raceTrackMapper.toDriverEntity(raceTrackDto);
        raceTrackRepo.save(driverEntity);
    }
}
