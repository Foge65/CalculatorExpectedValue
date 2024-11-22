package team.firestorm.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DesiredProfitControllerTest {

    @MockBean
    private DesiredProfitController controller;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getData() {
    }

    @Test
    void setDesiredProfit() {
    }

    @Test
    void setRoom() {
    }

    @Test
    void setBuyIn() {

    }

    @Test
    void setExpChipsT() {
    }

    @Test
    void getExpEVT() {
    }

    @Test
    void setTables() {
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
    void getRequiredHours() {
    }

    @Test
    void getDollarPerHour() {
    }
}