package com.andersen.training.training_refactoring.controller;

import com.andersen.training.training_refactoring.dto.RaceTrackDto;
import com.andersen.training.training_refactoring.service.RaceTrackService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RaceTrackController.class)
class RaceTrackControllerTest {

    private static final String RACE_TRACK_API_BASE_PATH = "/api/v1/race-track";

    @MockitoBean
    RaceTrackService raceTrackService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void addDRaceTrack_withValidDto_returnsCreatedStatus() throws Exception {
        RaceTrackDto raceTrackDto = RaceTrackDto.builder()
                .trackName("Circuit de Monaco")
                .country("Monaco")
                .build();

        String serializedRaceTrackDto = objectMapper.writeValueAsString(raceTrackDto);

        mockMvc.perform(post(RACE_TRACK_API_BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedRaceTrackDto))
                .andExpect(status().isCreated());

        verify(raceTrackService).addRaceTrack(refEq(raceTrackDto));
    }

    @Test
    void addDRaceEvent_withInvalidDto_returnsBadRequestStatus() throws Exception {
        RaceTrackDto invalidRaceTrackDto = RaceTrackDto.builder()
                .trackName("   Circuit554!")
                .country("M")
                .build();

        String serializedRaceTrackDto = objectMapper.writeValueAsString(invalidRaceTrackDto);

        mockMvc.perform(post(RACE_TRACK_API_BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedRaceTrackDto))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.title").value(HttpStatus.BAD_REQUEST.getReasonPhrase()));

        verifyNoInteractions(raceTrackService);
    }
}
