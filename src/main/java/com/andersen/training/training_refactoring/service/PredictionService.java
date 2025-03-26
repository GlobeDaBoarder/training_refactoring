package com.andersen.training.training_refactoring.service;

import com.andersen.training.training_refactoring.entity.Driver;
import com.andersen.training.training_refactoring.exception.DriverNotFoundException;
import com.andersen.training.training_refactoring.repo.DriverRepo;
import com.andersen.training.training_refactoring.repo.RaceResultRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PredictionService {

    private static final Map<Integer, Double> POSITION_WEIGHTS = Map.of(
            1, 1.0,
            2, 0.10,
            3, 0.05,
            4, 0.02,
            5, 0.01);

    private final RaceResultRepo raceResultRepo;
    private final DriverRepo driverRepo;

    public String predictWinningChance(Long driverId) {
        Driver driver = driverRepo.findById(driverId)
                .orElseThrow(() -> new DriverNotFoundException(driverId));

        long totalRaces = raceResultRepo.countAllByDriver(driver);
        if (totalRaces == 0) {
            return formatAsPercent(0);
        }

        Map<Integer, Long> positionCounts = new HashMap<>();
        POSITION_WEIGHTS.keySet().forEach(position ->
            positionCounts.put(position, raceResultRepo.countAllByDriverAndFinishingPositionIs(driver, position)));

        double weightedScore = POSITION_WEIGHTS.entrySet().stream()
                .mapToDouble(entry -> entry.getValue() * positionCounts.get(entry.getKey()))
                .sum();

        double winningChance = weightedScore / totalRaces;

        return formatAsPercent(winningChance);
    }

    private static String formatAsPercent(double value) {
        NumberFormat percentFormatter = NumberFormat.getPercentInstance();
        percentFormatter.setMinimumFractionDigits(2);
        return percentFormatter.format(value);
    }
}
