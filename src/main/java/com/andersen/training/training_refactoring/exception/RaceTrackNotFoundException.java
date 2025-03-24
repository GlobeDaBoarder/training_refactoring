package com.andersen.training.training_refactoring.exception;

public class RaceTrackNotFoundException extends NotFoundException {

    public RaceTrackNotFoundException(Long raceTrackId) {
        super("Race track with Id " + raceTrackId + " not found");
    }

    @Override
    public String getSecureMessage() {
        return "Race track with specified Id not found";
    }
}
