package team.firestorm.service.mesh;

import lombok.Getter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@Getter
public class StudyWithoutBacking implements Mesh {
    private final String name = "Обучение";

    public StudyWithoutBacking() {
        name();
    }

    @Override
    public String name() {
        return name;
    }
}
