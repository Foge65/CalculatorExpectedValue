package team.firestorm.service.room;

import lombok.Getter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@Getter
public class PokerStars implements Room {
    private double[] buyIns;
    private int[] rakes;
    private double[] winCoefficient;
    private double[] loseCoefficient;

    public PokerStars() {
        buyIns();
        rakes();
        winCoefficient();
        loseCoefficient();
    }

    @Override
    public double[] buyIns() {
        buyIns = new double[9];
        buyIns[0] = 0.25;
        buyIns[1] = 1;
        buyIns[2] = 2;
        buyIns[3] = 5;
        buyIns[4] = 5;
        buyIns[5] = 10;
        buyIns[6] = 25;
        buyIns[7] = 50;
        buyIns[8] = 100;
        return buyIns;
    }

    @Override
    public int[] rakes() {
        rakes = new int[9];
        rakes[0] = 8;
        rakes[1] = 7;
        rakes[2] = 7;
        rakes[3] = 7;
        rakes[4] = 8;
        rakes[5] = 7;
        rakes[6] = 6;
        rakes[7] = 6;
        rakes[8] = 5;
        return rakes;
    }

    @Override
    public double[] winCoefficient() {
        winCoefficient = new double[9];
        winCoefficient[0] = 1.7330501;
        winCoefficient[1] = 1.7330501;
        winCoefficient[2] = 1.7330501;
        winCoefficient[3] = 0.1760301;
        winCoefficient[4] = 1.7102501;
        winCoefficient[5] = 1.7630501;
        winCoefficient[6] = 1.7930501;
        winCoefficient[7] = 1.7930501;
        winCoefficient[8] = 1.8230501;
        return winCoefficient;
    }

    @Override
    public double[] loseCoefficient() {
        loseCoefficient = new double[9];
        loseCoefficient[0] = -0.9871249;
        loseCoefficient[1] = -0.9871249;
        loseCoefficient[2] = -0.9871249;
        loseCoefficient[3] = -0.0991149;
        loseCoefficient[4] = -0.9871249;
        loseCoefficient[5] = -0.9871249;
        loseCoefficient[6] = -0.9871249;
        loseCoefficient[7] = -0.9871249;
        loseCoefficient[8] = -0.9871249;
        return loseCoefficient;
    }
}
