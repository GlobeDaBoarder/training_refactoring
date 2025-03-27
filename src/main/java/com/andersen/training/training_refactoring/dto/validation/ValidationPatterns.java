package com.andersen.training.training_refactoring.dto.validation;

/// ### RegEx patterns used for constant validations
///
/// Usage example:
/// ```java
/// Pattern.compile(ValidationPatterns.ALPHABETIC_CHARACTERS);
/// ```
///
/// @author Gleb
/// @since 2.0.0
public final class ValidationPatterns {

    public static final String ALPHABETIC_CHARACTERS = "^[\\p{L}]+(?:\\s[\\p{L}]+)*$";
}
