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
        this.mockMvc.perform(get("/calcEV/getMeshes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(Meshes.values().length)))
                .andExpect(jsonPath("$[0]", is(Meshes.values()[0].toString())))
                .andExpect(jsonPath("$[1]", is(Meshes.values()[1].toString())))
                .andExpect(jsonPath("$[2]", is(Meshes.values()[2].toString())));
    }

    @Test
    void dollarEVTotal() throws Exception {
        this.mockMvc.perform(post("/calcEV/setBuyIn")
                        .param("buyIn", "10"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/calcEV/setDollarsPerHour")
                        .param("dollars", "10"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/calcEV/setHoursPerDay")
                        .param("hours", "3"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/calcEV/setDaysPerMonth")
                        .param("days", "30"))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/calcEV/dollarEVTotal1"))
                .andExpect(jsonPath("$", is(900.0)));
    }

    @Test
    void dollarEVTotal_PokerStars() throws Exception {
        this.mockMvc.perform(post("/calcEV/setRoom")
                        .param("room", "PokerStars"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/calcEV/setBuyIn")
                        .param("buyIn", "10"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/calcEV/setChipsEVFromTourney")
                        .param("chips", "50"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/calcEV/setTourneyPerPeriod")
                        .param("tourney", "5000"))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/calcEV/dollarEVTotal2"))
                .andExpect(jsonPath("$", is(1063.6300000000042)));
    }

    @Test
    void dollarEVTotal_Winamax() throws Exception {
        this.mockMvc.perform(post("/calcEV/setRoom")
                        .param("room", "Winamax"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/calcEV/setBuyIn")
                        .param("buyIn", "10"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/calcEV/setChipsEVFromTourney")
                        .param("chips", "50"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/calcEV/setTourneyPerPeriod")
                        .param("tourney", "5000"))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/calcEV/dollarEVTotal2"))
                .andExpect(jsonPath("$", is(243.31166666666238)));
    }
}
