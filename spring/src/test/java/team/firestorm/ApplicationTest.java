package team.firestorm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void mongoConnection_returnRealDatabaseName() {
        Assertions.assertNotNull(mongoTemplate);
        Assertions.assertEquals("firestorm_app", mongoTemplate.getDb().getName());
    }

}
