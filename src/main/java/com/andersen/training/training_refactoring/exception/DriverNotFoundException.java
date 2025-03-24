package com.andersen.training.training_refactoring.exception;

public class DriverNotFoundException extends NotFoundException {

    public DriverNotFoundException(Long driverId) {
        super("Driver with Id " + driverId + " not found");
    }

    @Override
    public String getSecureMessage() {
        return "Driver with specified Id not found";
    }
}
