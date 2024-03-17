package team.firestorm.entity;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
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
        buyIns = new double[8];
        buyIns[0] = 0.25;
        buyIns[1] = 1;
        buyIns[2] = 2;
        buyIns[3] = 5;
        buyIns[4] = 10;
        buyIns[5] = 25;
        buyIns[6] = 50;
        buyIns[7] = 100;
        return buyIns;
    }

    @Override
    public int[] rakes() {
        rakes = new int[4];
        rakes[0] = 8;
        rakes[1] = 7;
        rakes[2] = 6;
        rakes[3] = 5;
        return rakes;
    }

    @Override
    public double[] winCoefficient() {
        winCoefficient = new double[6];
        winCoefficient[0] = 1.7330501;
        winCoefficient[1] = 0.1760301;
        winCoefficient[2] = 1.7102501;
        winCoefficient[3] = 1.7630501;
        winCoefficient[4] = 1.7930501;
        winCoefficient[5] = 1.8230501;
        return winCoefficient;
    }

    @Override
    public double[] loseCoefficient() {
        loseCoefficient = new double[6];
        loseCoefficient[0] = -0.9871249;
        loseCoefficient[1] = -0.0991149;
        loseCoefficient[2] = -0.9871249;
        loseCoefficient[3] = -0.9871249;
        loseCoefficient[4] = -0.9871249;
        loseCoefficient[5] = -0.9871249;
        return loseCoefficient;
    }
}
