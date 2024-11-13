package team.firestorm.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import team.firestorm.service.mesh.Meshes;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class GetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void meshes() throws Exception {
        this.mockMvc.perform(get("/getMeshes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(Meshes.values().length)))
                .andExpect(jsonPath("$[0]", is(Meshes.values()[0].toString())))
                .andExpect(jsonPath("$[1]", is(Meshes.values()[1].toString())))
                .andExpect(jsonPath("$[2]", is(Meshes.values()[2].toString())));
    }

    @Test
    void dollarEVTotal() throws Exception {
        this.mockMvc.perform(post("/setBuyIn1")
                        .param("buyIn", "10"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/setDollarsPerHour")
                        .param("dollars", "10"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/setHoursPerDay")
                        .param("hours", "3"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/setDaysPerMonth")
                        .param("days", "30"))
                .andExpect(status().isOk());
    }

    @Test
    void dollarEVTotal_PokerStars() throws Exception {
        this.mockMvc.perform(post("/setRoom1")
                        .param("room", "PokerStars"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/setBuyIn1")
                        .param("buyIn", "10"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/setChipsEVFromTourney")
                        .param("chips", "50"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/setTourneyPerPeriod")
                        .param("tourney", "5000"))
                .andExpect(status().isOk());
    }

    @Test
    void dollarEVTotal_Winamax() throws Exception {
        this.mockMvc.perform(post("/setRoom1")
                        .param("room", "Winamax"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/setBuyIn1")
                        .param("buyIn", "10"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/setChipsEVFromTourney")
                        .param("chips", "50"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/setTourneyPerPeriod")
                        .param("tourney", "5000"))
                .andExpect(status().isOk());
    }
}
