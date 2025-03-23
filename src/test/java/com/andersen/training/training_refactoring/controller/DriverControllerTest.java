package com.andersen.training.training_refactoring.controller;

import com.andersen.training.training_refactoring.dto.DriverDto;
import com.andersen.training.training_refactoring.service.DriverService;
import com.andersen.training.training_refactoring.service.PredictionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void addDriver_withValidDto_returnsCreated() throws Exception {
        DriverDto driverDto = DriverDto.builder()
                .driverName("Luis Hamilton")
                .carBrand("Mercedes")
                .build();

        String serializedDriverDto = objectMapper.writer().writeValueAsString(driverDto);

        mockMvc.perform(post(DRIVER_API_BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(serializedDriverDto))
                .andExpect(status().isCreated());

        verify(driverService).addDriver(refEq(driverDto));
    }

    @Test
    void addDriver_withInvalidDto_returnsBadRequest() throws Exception {
        DriverDto driverDto = DriverDto.builder()
                .driverName("Luis123!")
                .carBrand("A")
                .build();

        String serializedDriverDto = objectMapper.writer().writeValueAsString(driverDto);

        mockMvc.perform(post(DRIVER_API_BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedDriverDto))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(driverService);
    }

    @Test
    void predictDriverWinningChance() {
    }
}
