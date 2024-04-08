package team.firestorm.service.mesh;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class BackingWithoutStudy implements Mesh {
    private final String name = "Бекинг без обучения";

    public BackingWithoutStudy() {
        name();
    }

    @Override
    public String name() {
        return name;
    }
}
