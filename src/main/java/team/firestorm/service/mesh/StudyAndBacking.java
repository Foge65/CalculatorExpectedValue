package team.firestorm.service.mesh;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class StudyAndBacking implements Mesh {
    private final String name = "Обучение и бекинг";

    public StudyAndBacking() {
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
