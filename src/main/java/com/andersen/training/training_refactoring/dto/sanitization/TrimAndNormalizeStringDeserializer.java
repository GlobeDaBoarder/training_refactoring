package com.andersen.training.training_refactoring.dto.sanitization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/// ### Custom deserializer for trimming and normalizing whitespace in strings.'
//
/// / This deserializer is used to ensure that strings are trimmed of leading and trailing whitespace,
/// and that multiple spaces within the string are replaced with a single space.
/// This is particularly useful for fields that require clean and consistent string input.
/// '
/// Example usage:
/// ```
///
///@Builder
///public record ExampleDto(
///
///        @JsonDeserialize(using = TrimAndNormalizeStringDeserializer.class)
///        String fieldName,
///) {
///}
/// ```
/// @author Gleb
/// @since 2.0.0
public class TrimAndNormalizeStringDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String value = parser.getValueAsString();
        if (value == null)
            return null;

        return value.trim().replaceAll("\\s+", " ");
    }
}
