package com.andersen.training.training_refactoring.controller;

import com.andersen.training.training_refactoring.dto.F1RaceResultsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import java.util.List;

/// This interface defines the legacy F1 API using Swagger annotations, used for generating API documentation.
///
/// @deprecated in favour of {@link DriverApi}, {@link RaceEventApi} and  {@link RaceTrackApi}
/// @author Gleb
/// @since 2.0.0
@Tag(
        name = "F1 Race (Legacy)",
        description = "Legacy API controller for creating race events, tracks, drivers and their results in a single request. Deprecated in favour of DriverController, RaceEventController, and RaceTrackController."
)
@Deprecated
public interface F1RaceApi {

    @Operation(
            summary = "Save race results",
            description = "Saves race events, tracks, drivers and their results in a single request. This endpoint is deprecated.",
            deprecated = true,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Race results saved successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid race results supplied",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(implementation = ProblemDetail.class)
                            )
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(implementation = ProblemDetail.class)
                            )
                    )
            }
    )
    ResponseEntity<Void> saveRaceResults(
            @RequestBody(
                    description = "List of race results to be saved",
                    required = true,
                    content = @Content(schema = @Schema(implementation = F1RaceResultsDto.class))
            )
            List<F1RaceResultsDto> races
    );

    @Operation(
            summary = "Get driver winning chance",
            description = "Calculates the winning chance for a driver. This endpoint is deprecated.",
            deprecated = true,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Winning chance retrieved successfully",
                            content = @Content(schema = @Schema(implementation = Double.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid driver parameter supplied",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(implementation = ProblemDetail.class)
                            )
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(implementation = ProblemDetail.class)
                            )
                    )
            }
    )
    ResponseEntity<Double> getWinningChance(
            @Parameter(
                    description = "Legacy driver identifier (driver's name)",
                    required = true,
                    example = "Hamilton"
            )
            String driver
    );
}
