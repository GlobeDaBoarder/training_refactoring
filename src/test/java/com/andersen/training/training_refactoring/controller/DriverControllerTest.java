package com.andersen.training.training_refactoring.controller;

import com.andersen.training.training_refactoring.dto.DriverDto;
import com.andersen.training.training_refactoring.exception.DriverNotFoundException;
import com.andersen.training.training_refactoring.service.DriverService;
import com.andersen.training.training_refactoring.service.PredictionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DriverController.class)
@ExtendWith(MockitoExtension.class)
class DriverControllerTest {

    private static final String DRIVER_API_BASE_PATH = "/api/v1/driver";

    @MockitoBean
    DriverService driverService;

    @MockitoBean
    PredictionService predictionService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void addDriver_withValidDto_returnsCreatedStatus() throws Exception {
        DriverDto driverDto = DriverDto.builder()
                .driverName("Luis Hamilton")
                .carBrand("Mercedes")
                .build();

        String serializedDriverDto = objectMapper.writeValueAsString(driverDto);

        mockMvc.perform(post(DRIVER_API_BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(serializedDriverDto))
                .andExpect(status().isCreated());

        verify(driverService).addDriver(refEq(driverDto));
    }

    @Test
    void addDriver_withInvalidDto_returnsBadRequestStatus() throws Exception {
        DriverDto invalidDriverDto = DriverDto.builder()
                .driverName("Luis123!")
                .carBrand("A")
                .build();

        String serializedDriverDto = objectMapper.writeValueAsString(invalidDriverDto);

        mockMvc.perform(post(DRIVER_API_BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedDriverDto))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.title").value(HttpStatus.BAD_REQUEST.getReasonPhrase()));

        verifyNoInteractions(driverService);
    }

    @Test
    void predictDriverWinningChance_withExistingDriver_returnsOkStatusAndWinningChance() throws Exception {
        Long driverId = 1L;
        String winningChance = "50%";
        when(predictionService.predictWinningChance(driverId))
                .thenReturn(winningChance);

        mockMvc.perform(get(DRIVER_API_BASE_PATH + "/{driverId}/winning-chance", driverId))
                .andExpect(status().isOk())
                .andExpect(content().string(winningChance));

    }

    @Test
    void predictDriverWinningChance_withNonExistingDriver_returnsNotFoundStatus() throws Exception {
        Long driverId = 1L;
        when(predictionService.predictWinningChance(driverId))
                .thenThrow(DriverNotFoundException.class);

        mockMvc.perform(get(DRIVER_API_BASE_PATH + "/{driverId}/winning-chance", driverId))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.title").value(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }
}
