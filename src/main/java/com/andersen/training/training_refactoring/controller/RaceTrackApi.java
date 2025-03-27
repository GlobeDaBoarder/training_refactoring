package com.andersen.training.training_refactoring.controller;

import com.andersen.training.training_refactoring.dto.RaceTrackDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ProblemDetail;

/// This interface defines the racetrack API using Swagger annotations, used for generating API documentation.
///
/// @author Gleb
/// @since 2.0.0
@Tag(name = "Race Track", description = "API for managing race tracks")
public interface RaceTrackApi {

    @Operation(
            summary = "Create a new race track",
            description = "Registers a new race track in the system using the provided race track details.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Race track created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid race track data supplied",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(implementation = ProblemDetail.class)))
            }
    )
    void addRaceTrack(
            @RequestBody(
                    description = "Race track object that needs to be added",
                    required = true,
                    content = @Content(schema = @Schema(implementation = RaceTrackDto.class))
            )
            RaceTrackDto raceTrackDto
    );
}