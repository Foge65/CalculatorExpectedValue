package team.firestorm.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import team.firestorm.entity.LkProfileEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LkProfileRepositoryTest {

    @MockBean
    private LkProfileRepository repository;

    @Test
    void findAccessToken() {
        LkProfileEntity mockEntity = new LkProfileEntity();
        mockEntity.setAccessToken("testToken");
        mockEntity.setUsername("testUsername");

        Mockito.when(repository.findLkProfileEntityByAccessToken("testToken"))
                .thenReturn(mockEntity);

        LkProfileEntity entity = repository.findLkProfileEntityByAccessToken("testToken");

        Assertions.assertNotNull(entity);
        Assertions.assertEquals("testToken", entity.getAccessToken());
        Mockito.verify(repository, Mockito.times(1))
                .findLkProfileEntityByAccessToken("testToken");
    }

}
