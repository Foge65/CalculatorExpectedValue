package team.firestorm.room;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class Winamax implements Room {
    private final String name = "Winamax";
    private double[] buyIns;
    private int[] rakes;
    private double[] winCoefficient;
    private double[] loseCoefficient;

    public Winamax() {
        name();
        buyIns();
        rakes();
        winCoefficient();
        loseCoefficient();
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public double[] buyIns() {
        buyIns = new double[9];
        buyIns[0] = 0.25;
        buyIns[1] = 0.5;
        buyIns[2] = 1;
        buyIns[3] = 2;
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
        rakes[4] = 7;
        rakes[5] = 7;
        rakes[6] = 7;
        rakes[7] = 7;
        rakes[8] = 7;
        return rakes;
    }

    @Override
    public double[] winCoefficient() {
        winCoefficient = new double[9];
        winCoefficient[0] = 1.7020040;
        winCoefficient[1] = 0;
        winCoefficient[2] = 1.7280079;
        winCoefficient[3] = 1.7190076;
        winCoefficient[4] = 1.7280077;
        winCoefficient[5] = 1.7280079;
        winCoefficient[6] = 1.7280085;
        winCoefficient[7] = 1.7280095;
        winCoefficient[8] = 1.7160240;
        return winCoefficient;
    }

    @Override
    public double[] loseCoefficient() {
        loseCoefficient = new double[9];
        loseCoefficient[0] = -0.9870000;
        loseCoefficient[1] = -0;
        loseCoefficient[2] = -0.9927421;
        loseCoefficient[3] = -0.9932424;
        loseCoefficient[4] = -0.9927423;
        loseCoefficient[5] = -0.9927421;
        loseCoefficient[6] = -0.9927415;
        loseCoefficient[7] = -0.9927405;
        loseCoefficient[8] = -0.9929760;
        return loseCoefficient;
    }
}
