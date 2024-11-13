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

    public void setRake1(double buyIn) {
        int index = coefficient.findSelectedCoefficient(room.buyIns(), buyIn);
        int[] rakes = room.rakes();
        this.modelRepository.setRake1(rakes[index]);
    }

    public void setRake2(double buyIn) {
        int index = coefficient.findSelectedCoefficient(room.buyIns(), buyIn);
        int[] rakes = room.rakes();
        this.modelRepository.setRake2(rakes[index]);
    }

}
