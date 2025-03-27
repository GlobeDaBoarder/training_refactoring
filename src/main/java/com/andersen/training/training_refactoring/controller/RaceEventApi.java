package com.andersen.training.training_refactoring.controller;

import com.andersen.training.training_refactoring.dto.RaceEventDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ProblemDetail;

/// This interface defines the race events API using Swagger annotations, used for generating API documentation.
///
/// @author Gleb
/// @since 2.0.0
@Tag(name = "Race Event", description = "API for managing race events")
public interface RaceEventApi {

    @Operation(
            summary = "Create a new race event",
            description = "Registers a new race event in the system using the provided race event details.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Race event created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid race event data supplied",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(responseCode = "404", description = "Race track or driver not found",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(implementation = ProblemDetail.class)))
            }
    )
    void addRaceEvent(
            @RequestBody(
                    description = "Race event object that needs to be added",
                    required = true,
                    content = @Content(schema = @Schema(implementation = RaceEventDto.class))
            )
            RaceEventDto raceEventDto
    );
}
