package com.andersen.training.training_refactoring.dto.validation.validator;

import com.andersen.training.training_refactoring.dto.validation.ValidationPatterns;
import com.andersen.training.training_refactoring.dto.validation.annotation.Alphabetic;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/// ### Validator used for @{link Alphabetic} annotation.
///
/// @author Gleb
/// @since 2.0.0
public class AlphabeticValidator implements ConstraintValidator<Alphabetic, String> {

    private Pattern alphabeticPattern;

    @Override
    public void initialize(Alphabetic constraintAnnotation) {
        alphabeticPattern = Pattern.compile(ValidationPatterns.ALPHABETIC_CHARACTERS);
    }

    @Override
    public boolean isValid(String alphabeticValue, ConstraintValidatorContext constraintValidatorContext) {
        if (alphabeticValue == null)
            return true;

        return alphabeticPattern.matcher(alphabeticValue).matches();
    }
}
