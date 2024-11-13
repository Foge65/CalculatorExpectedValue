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

    public void setWinCoefficient1(double buyIn) {
        int index = findSelectedCoefficient(room.buyIns(), buyIn);
        double[] winCoeffs = modelRepository.getWinCoefficients1();
        this.modelRepository.setWinCoefficient1(winCoeffs[index]);
    }

    public void setLoseCoefficient1(double buyIn) {
        int index = findSelectedCoefficient(room.buyIns(), buyIn);
        double[] loseCoeffs = modelRepository.getLoseCoefficients1();
        this.modelRepository.setLoseCoefficient1(loseCoeffs[index]);
    }

    public void setWinCoefficient2(double buyIn) {
        int index = findSelectedCoefficient(room.buyIns(), buyIn);
        double[] winCoeffs = modelRepository.getWinCoefficients2();
        this.modelRepository.setWinCoefficient2(winCoeffs[index]);
    }

    public void setLoseCoefficient2(double buyIn) {
        int index = findSelectedCoefficient(room.buyIns(), buyIn);
        double[] loseCoeffs = modelRepository.getLoseCoefficients2();
        this.modelRepository.setLoseCoefficient2(loseCoeffs[index]);
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
