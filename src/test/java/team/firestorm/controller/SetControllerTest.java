package team.firestorm.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class SetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void room() throws Exception {
        this.mockMvc.perform(post("/calcEV/setRoom")
                        .param("room", "PokerStars"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/calcEV/setRoom")
                        .param("room", "Winamax"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/calcEV/setRoom")
                        .param("room", "iPoker"))
                .andExpect(status().isOk());
    }

    @Test
    void mesh() throws Exception {
        this.mockMvc.perform(post("/calcEV/setMesh")
                        .param("mesh", "Study"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/calcEV/setMesh")
                        .param("mesh", "BackingWithoutStudy"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/calcEV/setMesh")
                        .param("mesh", "StudyAndBacking"))
                .andExpect(status().isOk());
    }
}
