package team.firestorm.service.room;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class Winamax implements Room {
    private double[] buyIns;
    private int[] rakes;
    private double[] winCoefficient;
    private double[] loseCoefficient;

    @PostConstruct
    public void init() {
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
        rakes = new int[8];
        rakes[0] = 8;
        rakes[1] = 7;
        rakes[2] = 7;
        rakes[3] = 7;
        rakes[4] = 7;
        rakes[5] = 7;
        rakes[6] = 7;
        rakes[7] = 7;
        return rakes;
    }

    @Override
    public double[] winCoefficient() {
        winCoefficient = new double[8];
        winCoefficient[0] = 1.7020040;
        winCoefficient[1] = 1.7280079;
        winCoefficient[2] = 1.7190076;
        winCoefficient[3] = 1.7280077;
        winCoefficient[4] = 1.7280079;
        winCoefficient[5] = 1.7280085;
        winCoefficient[6] = 1.7280095;
        winCoefficient[7] = 1.7160240;
        return winCoefficient;
    }

    @Override
    public double[] loseCoefficient() {
        loseCoefficient = new double[8];
        loseCoefficient[0] = -0.9870000;
        loseCoefficient[1] = -0.9927421;
        loseCoefficient[2] = -0.9932424;
        loseCoefficient[3] = -0.9927423;
        loseCoefficient[4] = -0.9927421;
        loseCoefficient[5] = -0.9927415;
        loseCoefficient[6] = -0.9927405;
        loseCoefficient[7] = -0.9929760;
        return loseCoefficient;
    }
}
