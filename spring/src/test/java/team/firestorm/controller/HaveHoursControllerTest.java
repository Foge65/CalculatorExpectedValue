package team.firestorm.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class HaveHoursControllerTest {

    @MockBean
    private HaveHoursController controller;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getData() {
    }

    @Test
    void setHaveHours() {
    }

    @Test
    void setTables() {
    }

    @Test
    void setRoom() {
    }

    @Test
    void setBuyIn() {
    }

    @Test
    void getBuyIns() {
    }

    @Test
    void setExpChipsT() {
    }

    @Test
    void getExpEVT() {
    }

    @Test
    void setRakebackPct() {
    }

    @Test
    void setMesh() {
    }

    @Test
    void getRollback() {
    }

    @Test
    void getRequiredTourneys() {
    }

    @Test
    void getEstimatedExpectation() {
    }

    @Test
    void getDollarPerHour() {
    }
}