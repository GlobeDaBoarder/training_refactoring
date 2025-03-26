package com.andersen.training.training_refactoring.service;

import com.andersen.training.training_refactoring.entity.Driver;
import com.andersen.training.training_refactoring.repo.DriverRepo;
import com.andersen.training.training_refactoring.repo.RaceResultRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PredictionServiceTest {

    @Mock
    private RaceResultRepo raceResultRepo;

    @Mock
    private DriverRepo driverRepo;

    @InjectMocks
    private PredictionService predictionService;

    @Test
    public void testPredictWinningChance_totalRacesZero() {
        Long driverId = 1L;
        Driver driver = new Driver();

        when(driverRepo.findById(driverId))
                .thenReturn(Optional.of(driver));
        when(raceResultRepo.countAllByDriver(driver))
                .thenReturn(0L);

        String result = predictionService.predictWinningChance(driverId);

        assertThat(result)
                .isEqualTo("0.00%");
    }

    @Test
    public void testPredictWinningChance_mixedPositions() {
        Long driverId = 1L;
        Driver driver = new Driver();

        when(driverRepo.findById(driverId))
                .thenReturn(Optional.of(driver));
        when(raceResultRepo.countAllByDriver(driver))
                .thenReturn(10L);

        when(raceResultRepo.countAllByDriverAndFinishingPositionIs(driver, 1))
                .thenReturn(3L);
        when(raceResultRepo.countAllByDriverAndFinishingPositionIs(driver, 2))
                .thenReturn(2L);
        when(raceResultRepo.countAllByDriverAndFinishingPositionIs(driver, 3))
                .thenReturn(1L);
        when(raceResultRepo.countAllByDriverAndFinishingPositionIs(driver, 4))
                .thenReturn(1L);
        when(raceResultRepo.countAllByDriverAndFinishingPositionIs(driver, 5))
                .thenReturn(1L);

        // weightedScore = 3*1.0 + 2*0.10 + 1*0.05 + 1*0.02 + 1*0.01 = 3 + 0.2 + 0.05 + 0.02 + 0.01 = 3.28
        // winningChance = 3.28 / 10 = 0.328
        String result = predictionService.predictWinningChance(driverId);

        assertThat(result)
                .isEqualTo("32.80%");
    }
}
