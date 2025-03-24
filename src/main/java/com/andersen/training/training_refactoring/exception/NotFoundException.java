package com.andersen.training.training_refactoring.exception;

public abstract class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public abstract String getSecureMessage();
}
