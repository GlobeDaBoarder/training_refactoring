package com.andersen.training.training_refactoring.service;

import org.springframework.stereotype.Service;

import java.text.NumberFormat;

@Service
public class PredictionService {
    public String predictWinningChance() {
        NumberFormat percentFormatter = NumberFormat.getPercentInstance();
        percentFormatter.setMinimumFractionDigits(2);

        Double winningChance = 0.0;


        return percentFormatter.format(winningChance);
    }

    // TOD0
    // 1. number of 1st places driver took previously
    // 2. "Power" of the team with for example 0.2 multiplier. Come up with power indexes for different cars
    // 3. Calculate chance including previous positions within top 5. Come up with cooficients for getting 2nd, 3rd, etc places.
}
