package com.andersen.training.training_refactoring.controller;

import com.andersen.training.training_refactoring.dto.DriverDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ProblemDetail;

@Tag(name = "Driver", description = "API for managing drivers and predicting their winning chances")
public interface DriverApi {

    @Operation(
            summary = "Create a new driver",
            description = "Registers a new driver in the system using the provided driver details.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Driver created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid driver data supplied",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(responseCode = "404", description = "Driver not found",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(responseCode = "500",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(implementation = ProblemDetail.class)))
            }
    )
    void addDriver(
            @RequestBody(
                    description = "Driver object that needs to be added",
                    required = true,
                    content = @Content(schema = @Schema(implementation = DriverDto.class))
            )
            DriverDto driverDto
    );

    @Operation(
            summary = "Get driver winning chance",
            description = "Calculates and returns the winning chance for the specified driver.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Winning chance successfully retrieved",
                            content = @Content(
                                    schema = @Schema(implementation = String.class, example = "0.75%"))),
                    @ApiResponse(responseCode = "404", description = "Driver not found",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(responseCode = "500",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(implementation = ProblemDetail.class)))
            }
    )
    String predictDriverWinningChance(
            @Parameter(
                    description = "Unique identifier of the driver",
                    required = true,
                    example = "123"
            )
            Long driverId
    );
}
