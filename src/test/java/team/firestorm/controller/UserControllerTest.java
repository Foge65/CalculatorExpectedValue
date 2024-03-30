package team.firestorm.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import team.firestorm.service.mesh.Meshes;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void setRoom() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/calcEV/setRoom")
                        .param("room", "PokerStars"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/calcEV/setRoom")
                        .param("room", "Winamax"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/calcEV/setRoom")
                        .param("room", "iPoker"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getMeshes() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/calcEV/getMeshes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(Meshes.values().length)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]", Matchers.is(Meshes.values()[0].toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]", Matchers.is(Meshes.values()[1].toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2]", Matchers.is(Meshes.values()[2].toString())));
    }

    @Test
    void setMesh() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/calcEV/setMesh")
                        .param("mesh", "Study"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/calcEV/setMesh")
                        .param("mesh", "BackingWithoutStudy"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/calcEV/setMesh")
                        .param("mesh", "StudyAndBacking"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}