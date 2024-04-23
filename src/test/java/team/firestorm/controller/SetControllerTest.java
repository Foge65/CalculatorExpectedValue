package team.firestorm.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import team.firestorm.repository.ModelRepository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SetControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ModelRepository modelRepository;

    @Test
    void buyIn() throws Exception {
        this.mockMvc.perform(post("/calcEV/setRoom")
                        .param("room", "iPoker"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/calcEV/setBuyIn")
                        .param("buyIn", "25"))
                .andExpect(status().isOk());
        assertFalse(arrayContainsValue(this.modelRepository.getBuyIns(), 25));

        this.mockMvc.perform(post("/calcEV/setBuyIn")
                        .param("buyIn", "20"))
                .andExpect(status().isOk());
        assertTrue(arrayContainsValue(this.modelRepository.getBuyIns(), 20));
    }

    private boolean arrayContainsValue(double[] array, int value) {
        for (double element : array) {
            if (element == value) {
                return true;
            }
        }
        return false;
    }

    @Test
    void mesh() throws Exception {
        this.mockMvc.perform(post("/calcEV/setMesh")
                        .param("mesh", "BackingWithStudy"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/calcEV/setMesh")
                        .param("mesh", "BackingWithoutStudy"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/calcEV/setMesh")
                        .param("mesh", "StudyWithoutBacking"))
                .andExpect(status().isOk());
    }
}
