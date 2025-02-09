package team.firestorm.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import team.firestorm.entity.LkProfileEntity;

@Repository
public interface LkProfileRepository extends MongoRepository<LkProfileEntity, String> {
    LkProfileEntity findLkProfileEntityByAccessToken(String accessToken);
}
