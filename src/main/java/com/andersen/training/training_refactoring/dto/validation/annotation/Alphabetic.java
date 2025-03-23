package com.andersen.training.training_refactoring.dto.validation.annotation;

import com.andersen.training.training_refactoring.dto.validation.validator.AlphabeticValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = AlphabeticValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Alphabetic {
    String message() default "Must contain only alphabetic characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
