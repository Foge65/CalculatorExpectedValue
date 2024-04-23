package team.firestorm.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team.firestorm.repository.ModelRepository;
import team.firestorm.service.room.Room;

@Service
@AllArgsConstructor
public class Rake {
    private final ModelRepository modelRepository;
    private final Room room;
    private final Coefficient coefficient;

    public void setRake(double buyIn) {
        int index = coefficient.matchIndexFromArray(room.buyIns(), buyIn);
        int[] rakes = room.rakes();
        this.modelRepository.setRake(rakes[index]);
    }
}
