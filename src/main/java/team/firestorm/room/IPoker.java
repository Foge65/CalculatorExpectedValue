package team.firestorm.room;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class IPoker implements Room {
    private final String name = "iPoker";
    private double[] buyIns;
    private int[] rakes;
    private double[] winCoefficient;
    private double[] loseCoefficient;

    public IPoker() {
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
        return new double[0];
    }

    @Override
    public int[] rakes() {
        return new int[0];
    }

    @Override
    public double[] winCoefficient() {
        return new double[0];
    }

    @Override
    public double[] loseCoefficient() {
        return new double[0];
    }
}
