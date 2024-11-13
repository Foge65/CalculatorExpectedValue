package team.firestorm.service.mesh;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ClearProfit implements Mesh {
    private final String name = "Чистый профит";

    public ClearProfit() {
        name();
    }

    @Override
    public String name() {
        return name;
    }
}
