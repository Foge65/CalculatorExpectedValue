package team.firestorm.service.mesh;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class BackingWithoutStudy implements Mesh {
    private final String name = "Бекинг без обучения";

    public BackingWithoutStudy() {
        name();
        profitRatio();
        rollback();
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int profitRatio() {
        return 0;
    }

    @Override
    public int rollback() {
        return 0;
    }
}
