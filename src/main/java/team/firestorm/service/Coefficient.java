package team.firestorm.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team.firestorm.repository.ModelRepository;
import team.firestorm.service.room.Room;

@Service
@AllArgsConstructor
public class Coefficient {
    private final ModelRepository modelRepository;
    private final Room room;

    public void setWinCoefficient(double buyIn) {
        int index = findSelectedCoefficient(room.buyIns(), buyIn);
        double[] winCoeffs = modelRepository.getWinCoefficients();
        this.modelRepository.setWinCoefficient(winCoeffs[index]);
    }

    public void setLoseCoefficient(double buyIn) {
        int index = findSelectedCoefficient(room.buyIns(), buyIn);
        double[] loseCoeffs = modelRepository.getLoseCoefficients();
        this.modelRepository.setLoseCoefficient(loseCoeffs[index]);
    }

    public int findSelectedCoefficient(double[] array, double buyIn) {
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == buyIn) {
                index = i;
                break;
            }
        }
        return index;
    }

}
