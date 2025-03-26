package com.andersen.training.training_refactoring.service;

import com.andersen.training.training_refactoring.entity.Driver;
import com.andersen.training.training_refactoring.exception.DriverNotFoundException;
import com.andersen.training.training_refactoring.repo.DriverRepo;
import com.andersen.training.training_refactoring.repo.RaceResultRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;

@Service
@RequiredArgsConstructor
public class PredictionService {

    private final RaceResultRepo raceResultRepo;
    private final DriverRepo driverRepo;

    // TODO enhance method business logic
    // 1. number of 1st places driver took previously
    // 2. "Power" of the team with for example 0.2 multiplier. Come up with power indexes for different cars
    // 3. Calculate chance including previous positions within top 5. Come up with cooficients for getting 2nd, 3rd, etc places.
    public String predictWinningChance(Long driverId) {
        Driver driver = driverRepo.findById(driverId)
                .orElseThrow(() -> new DriverNotFoundException(driverId));
        long totalRaces = raceResultRepo.countAllByDriver(driver);
        long wonRaces = raceResultRepo.countAllByDriverAndFinishingPositionIsFirst(driver);

        return calculatePrediction(totalRaces, wonRaces);
    }

    private static String calculatePrediction(long totalRaces, double wonRaces) {
        NumberFormat percentFormatter = NumberFormat.getPercentInstance();
        percentFormatter.setMinimumFractionDigits(2);

        double winningChance = totalRaces == 0 ? 0 : wonRaces / totalRaces;

        return percentFormatter.format(winningChance);
    }
}
