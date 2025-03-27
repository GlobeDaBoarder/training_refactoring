package com.andersen.training.training_refactoring.exception;

/// ### Custom exception for not found entities
///
/// Base exception for all `404` not found exceptions occurring in the application.
/// Extend this class to create specific not found exceptions for different entities.
///
/// @author Gleb
/// @since 2.0.0
public abstract class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public abstract String getSecureMessage();
}
