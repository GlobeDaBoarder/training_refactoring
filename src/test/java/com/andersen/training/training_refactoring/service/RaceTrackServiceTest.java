package com.andersen.training.training_refactoring.service;

import com.andersen.training.training_refactoring.dto.RaceTrackDto;
import com.andersen.training.training_refactoring.entity.RaceTrack;
import com.andersen.training.training_refactoring.mapper.RaceTrackMapperImpl;
import com.andersen.training.training_refactoring.repo.RaceTrackRepo;
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
class RaceTrackServiceTest {

    @Mock
    RaceTrackRepo raceTrackRepo;

    @Captor
    ArgumentCaptor<RaceTrack> raceTrackCaptor;

    RaceTrackService raceTrackService;

    @BeforeEach
    void setUp() {
        raceTrackService = new RaceTrackService(raceTrackRepo, new RaceTrackMapperImpl());
    }

    @Test
    void addRaceTrack_withRaceTrackDto_callsRaceTrackRepo() {
        RaceTrackDto raceTrackDto = new RaceTrackDto("Circuit de Monaco", "Monaco");
        when(raceTrackRepo.save(any()))
                .thenReturn(mock(RaceTrack.class));

        raceTrackService.addRaceTrack(raceTrackDto);

        verify(raceTrackRepo).save(raceTrackCaptor.capture());

        assertThat(raceTrackCaptor.getValue())
                .returns(raceTrackDto.trackName(), RaceTrack::getTrackName)
                .returns(raceTrackDto.country(), RaceTrack::getCountry);
    }
}