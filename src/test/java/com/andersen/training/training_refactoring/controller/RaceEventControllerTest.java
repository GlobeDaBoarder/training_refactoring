package com.andersen.training.training_refactoring.controller;

import com.andersen.training.training_refactoring.dto.RaceEventDto;
import com.andersen.training.training_refactoring.dto.RaceResultDto;
import com.andersen.training.training_refactoring.service.RaceEventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import static org.mockito.Mockito.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RaceEventController.class)
class RaceEventControllerTest {

    private static final String RACE_EVENT_API_BASE_PATH = "/api/v1/race-event";

    @MockitoBean
    RaceEventService raceEventService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void addDRaceEvent_withValidDto_returnsCreatedStatus() throws Exception {
        RaceEventDto raceEventDto = RaceEventDto.builder()
                .date(LocalDate.of(2025, 1, 1))
                .raceTrackId(1L)
                .raceResultDtos(Set.of(RaceResultDto.builder().build()))
                .build();

        String serializedRacEventDto = objectMapper.writeValueAsString(raceEventDto);

        mockMvc.perform(post(RACE_EVENT_API_BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedRacEventDto))
                .andExpect(status().isCreated());

        verify(raceEventService).addRaceEvent(refEq(raceEventDto));
    }

    @Test
    void addDRaceEvent_withInvalidDto_returnsBadRequestStatus() throws Exception {
        RaceEventDto invalidRaceEvent = RaceEventDto.builder()
                .date(LocalDate.now().plusYears(1))
                .raceTrackId(-1L)
                .raceResultDtos(Collections.emptySet())
                .build();

        String serializedRacEventDto = objectMapper.writeValueAsString(invalidRaceEvent);

        mockMvc.perform(post(RACE_EVENT_API_BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedRacEventDto))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.title").value(HttpStatus.BAD_REQUEST.getReasonPhrase()));

        verifyNoInteractions(raceEventService);
    }
}
