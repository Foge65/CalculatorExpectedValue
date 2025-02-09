package team.firestorm.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import team.firestorm.entity.LkProfileEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LkProfileServiceTest {

    @MockBean
    private LkProfileService service;

    @Test
    void findUserByToken() {
        LkProfileEntity mockEntity = new LkProfileEntity();
        mockEntity.setAccessToken("testToken");
        mockEntity.setUsername("testUsername");

        Mockito.when(service.findUserByToken("testToken")).thenReturn(mockEntity);

        LkProfileEntity entity = service.findUserByToken("testToken");
        Assertions.assertNotNull(entity);
        Assertions.assertEquals("testUsername", entity.getUsername());
        Assertions.assertEquals("testToken", entity.getAccessToken());
    }

}
