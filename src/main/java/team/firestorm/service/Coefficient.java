package team.firestorm.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team.firestorm.repository.Model;
import team.firestorm.service.room.Room;

@Service
@AllArgsConstructor
public class Coefficient {
    private final Model model;
    private final Room room;

    public void setWinCoefficient(double buyIn) {
        int index = matchIndexFromArray(room.buyIns(), buyIn);
        double[] winCoeffs = room.winCoefficient();
        this.model.setWinCoefficient(winCoeffs[index]);
    }

    public void setLoseCoefficient(double buyIn) {
        int index = matchIndexFromArray(room.buyIns(), buyIn);
        double[] loseCoeffs = room.loseCoefficient();
        this.model.setLoseCoefficient(loseCoeffs[index]);
    }

    private int matchIndexFromArray(double[] array, double value) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                index = i;
                break;
            }
        }
        return index;
    }
}
