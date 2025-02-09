package team.firestorm.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import team.firestorm.entity.LkProfileEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LkProfileRealRepositoryTest {

    @Autowired
    private LkProfileRepository repository;

    @Value("${foge65_token}")
    private String fogeToken;

    @Test
    void findAccessToken() {
        LkProfileEntity entity = repository.findLkProfileEntityByAccessToken(fogeToken);

        Assertions.assertNotNull(entity);
        Assertions.assertEquals(fogeToken, entity.getAccessToken());
        Assertions.assertEquals("foge65", entity.getUsername());
    }

}
