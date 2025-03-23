package com.andersen.training.training_refactoring.dto.sanitization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TrimAndNormalizeStringDeserializerTest {

    TrimAndNormalizeStringDeserializer deserializer = new TrimAndNormalizeStringDeserializer();
    ObjectMapper objectMapper = new ObjectMapper();

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of(null, null),
                Arguments.of("", ""),
                Arguments.of("   ", ""),
                Arguments.of("  Hello  ", "Hello"),
                Arguments.of("Hello   World", "Hello World"),
                Arguments.of("  Multiple    Spaces   In   Between  ", "Multiple Spaces In Between")
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void deserialize_givenVariousInputs_returnsNormalized(String input, String expected) throws IOException {
        String deserializationResult = deserializeString(input);

        assertThat(deserializationResult)
                .isEqualTo(expected);
    }

    private String deserializeString(String input) throws IOException {
        String jsonInput = input == null ? "null" : "\"" + input + "\"";
        JsonParser parser = objectMapper.getFactory().createParser(jsonInput);
        parser.nextToken();
        DeserializationContext context = objectMapper.getDeserializationContext();
        return deserializer.deserialize(parser, context);
    }
}
