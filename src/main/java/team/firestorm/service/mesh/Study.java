package team.firestorm.service.mesh;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class Study implements Mesh {
    @Override
    public int profitRatio() {
        return 0;
    }

    @Override
    public int rollback() {
        return 0;
    }
}
