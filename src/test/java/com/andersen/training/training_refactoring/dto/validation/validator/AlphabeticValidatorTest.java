package com.andersen.training.training_refactoring.dto.validation.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class AlphabeticValidatorTest {

    private AlphabeticValidator validator;

    @BeforeEach
    void setUp() {
        validator = new AlphabeticValidator();
        validator.initialize(null);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "Hamilton", "Lewis", "Max Verstappen", "Élodie", "José", "Zoë" })
    void isValid_withValidAlphabeticValues_returnsTrue(String input) {
        boolean result = validator.isValid(input, null);

        assertThat(result)
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = { "Hamilton123", "123", "Hamilton!", "   ", "" })
    void isValid_withNonAlphabeticValues_returnsFalse(String input) {
        boolean result = validator.isValid(input, null);

        assertThat(result)
                .isFalse();
    }
}
