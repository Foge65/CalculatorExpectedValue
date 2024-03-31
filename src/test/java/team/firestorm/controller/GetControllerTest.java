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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(printOnlyOnFailure = false)
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
}
