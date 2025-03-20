package com.andersen.training.training_refactoring.service;

import com.andersen.training.training_refactoring.dto.RaceEventDto;
import com.andersen.training.training_refactoring.dto.RaceResultDto;
import com.andersen.training.training_refactoring.entity.Driver;
import com.andersen.training.training_refactoring.entity.RaceEvent;
import com.andersen.training.training_refactoring.entity.RaceResult;
import com.andersen.training.training_refactoring.entity.RaceTrack;
import com.andersen.training.training_refactoring.repo.DriverRepo;
import com.andersen.training.training_refactoring.repo.RaceEventRepo;
import com.andersen.training.training_refactoring.repo.RaceTrackRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RaceEventServiceTest {

    static final int FINISHING_POSITION = 1;
    static final long DRIVER_ID = 1L;
    static final long TRACK_ID = 1L;
    static final LocalDate RACE_EVENT_DATE = LocalDate.of(2025, FINISHING_POSITION, FINISHING_POSITION);
    static final Driver DRIVER = new Driver();
    static final RaceTrack TRACK = new RaceTrack();

    @Mock
    RaceEventRepo raceEventRepo;

    @Mock
    DriverRepo driverRepo;

    @Mock
    RaceTrackRepo raceTrackRepo;

    @Captor
    ArgumentCaptor<RaceEvent> raceEventCaptor;

    @InjectMocks
    RaceEventService raceEventService;

    @Test
    void addRaceEvent() {

        RaceEventDto raceEventDto = buildRaceEventDto();
        RaceEvent expectedSavedRaceEvent = buildExpectedRaceEvent();

        when(driverRepo.getReferenceById(DRIVER_ID))
                .thenReturn(DRIVER);
        when(raceTrackRepo.getReferenceById(TRACK_ID))
                .thenReturn(TRACK);
        when(raceEventRepo.save(any()))
                .thenReturn(new RaceEvent());

        raceEventService.addRaceEvent(raceEventDto);

        verify(raceEventRepo).save(raceEventCaptor.capture());

        assertThat(raceEventCaptor.getValue())
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedSavedRaceEvent);
    }

    private RaceEventDto buildRaceEventDto() {
        return RaceEventDto.builder()
                .date(RACE_EVENT_DATE)
                .raceTrackId(TRACK_ID)
                .raceResultDtos(Set.of(
                        RaceResultDto.builder().driverId(DRIVER_ID).finishingPosition(FINISHING_POSITION).build()))
                .build();
    }

    private RaceEvent buildExpectedRaceEvent() {
        return RaceEvent.builder()
                .date(RACE_EVENT_DATE)
                .raceTrack(TRACK)
                .raceResults(Set.of(
                        RaceResult.builder().driver(DRIVER).finishingPosition(FINISHING_POSITION).build()))
                .build();
    }
}
