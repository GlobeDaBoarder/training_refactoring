package com.andersen.training.training_refactoring;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntegrationTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    @Order(1)
    void saveRaceResults_withValidInput_returns200() throws Exception {

        String raceResultsJson = new String(getClass().getClassLoader().getResourceAsStream("race_results.json").readAllBytes());

        MvcResult mvcResult = mockMvc.perform(post("/races/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(raceResultsJson))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();

        assertThat(responseContent)
                .isEqualTo("Saved");
    }

    @Test
    @Order(2)
    void getWinningChance_forLewisHamilton_returnsExpectedChance() throws Exception {

        double expectedWinningChance = 40.0;

        MvcResult mvcResult = mockMvc.perform(get("/races/chance/{driver}", "Lewis Hamilton"))
                .andExpect(status().isOk())
                .andReturn();

        double responseContent = Double.parseDouble(mvcResult.getResponse().getContentAsString());

        assertThat(responseContent)
                .isEqualTo(expectedWinningChance);
    }
}
