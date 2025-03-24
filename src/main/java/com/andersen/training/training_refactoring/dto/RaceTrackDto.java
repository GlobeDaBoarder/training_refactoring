package com.andersen.training.training_refactoring.dto;

import com.andersen.training.training_refactoring.dto.sanitization.TrimAndNormalizeStringDeserializer;
import com.andersen.training.training_refactoring.dto.validation.annotation.Alphabetic;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RaceTrackDto(

        @JsonDeserialize(using = TrimAndNormalizeStringDeserializer.class)
        @NotEmpty
        @Size(min = 2, max = 50)
        @Alphabetic
        String trackName,

        @JsonDeserialize(using = TrimAndNormalizeStringDeserializer.class)
        @NotEmpty
        @Size(min = 2, max = 50)
        @Alphabetic
        String country
) {
}
