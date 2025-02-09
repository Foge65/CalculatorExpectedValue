package team.firestorm.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(value = "lk_profiles")
@Data
public class LkProfileEntity {

    @Field(value = "accessToken")
    public String accessToken;

    @Field(value = "username")
    public String username;
}
