package team.firestorm.service.mesh;

import lombok.Getter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@Getter
public class Study implements Mesh {
    private final String name = "Обучение";

    public Study() {
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
