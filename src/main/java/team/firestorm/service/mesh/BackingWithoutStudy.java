package team.firestorm.service.mesh;

import org.springframework.stereotype.Service;

@Service
public class BackingWithoutStudy implements Mesh {
    @Override
    public int profitRatio() {
        return 0;
    }

    @Override
    public int rollback() {
        return 0;
    }
}
