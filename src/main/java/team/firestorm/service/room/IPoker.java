package team.firestorm.service.room;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class IPoker implements Room {
    private double[] buyIns;
    private int[] rakes;
    private double[] winCoefficient;
    private double[] loseCoefficient;

    public IPoker() {
        buyIns();
        rakes();
        winCoefficient();
        loseCoefficient();
    }

    @Override
    public double[] buyIns() {
        buyIns = new double[7];
        buyIns[0] = 1;
        buyIns[1] = 2;
        buyIns[2] = 5;
        buyIns[3] = 10;
        buyIns[4] = 20;
        buyIns[5] = 50;
        buyIns[6] = 100;
        return buyIns;
    }

    @Override
    public int[] rakes() {
        rakes = new int[9];
        rakes[0] = 7;
        rakes[1] = 7;
        rakes[2] = 7;
        rakes[3] = 7;
        rakes[4] = 7;
        rakes[5] = 7;
        rakes[6] = 7;
        return rakes;
    }

    @Override
    public double[] winCoefficient() {
        winCoefficient = new double[9];
        winCoefficient[0] = 1.77002;
        winCoefficient[1] = 1.77002;
        winCoefficient[2] = 1.77002;
        winCoefficient[3] = 1.77002;
        winCoefficient[4] = 1.76502;
        winCoefficient[5] = 1.76502;
        winCoefficient[6] = 1.76502;
        return winCoefficient;
    }

    @Override
    public double[] loseCoefficient() {
        loseCoefficient = new double[9];
        loseCoefficient[0] = -0.99998;
        loseCoefficient[1] = -0.99998;
        loseCoefficient[2] = -0.99998;
        loseCoefficient[3] = -0.99998;
        loseCoefficient[4] = -0.99748;
        loseCoefficient[5] = -0.99748;
        loseCoefficient[6] = -0.99748;
        return loseCoefficient;
    }
}
