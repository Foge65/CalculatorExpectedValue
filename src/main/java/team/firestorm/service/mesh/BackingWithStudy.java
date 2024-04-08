package team.firestorm.service.mesh;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class BackingWithStudy implements Mesh {
    private final String name = "Обучение и бекинг";

    public BackingWithStudy() {
        name();
    }

    @Override
    public String name() {
        return name;
    }
}
