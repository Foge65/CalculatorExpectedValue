package team.firestorm.out;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import team.firestorm.newdesign.ProfitCalc;
import team.firestorm.repository.ModelRepository;
import team.firestorm.service.mesh.BackingWithoutStudy;

class ProfitCalcTest {
    private ModelRepository modelRepository;
    private ProfitCalc profitCalc;

    @Test
    void requiredTourneys() {
        modelRepository = new ModelRepository();

        modelRepository.setDesiredProfit(1000);
        modelRepository.setBuyIn(10.0);
        modelRepository.setRake(7.0);
        modelRepository.setMesh(new BackingWithoutStudy());
        modelRepository.setRakebackPercent(35.0);
        modelRepository.setExpChipsEV(56);
        modelRepository.setWinCoefficient(1.7280079);
        modelRepository.setLoseCoefficient(-0.9927421);

        profitCalc = new ProfitCalc(modelRepository);
        double requiredTourneys = profitCalc.requiredTourneys();

        Assertions.assertEquals(2923.9639692564597, requiredTourneys);
    }

    @Test
    void rollbackPercent() {
        modelRepository = new ModelRepository();

        double buyIn = 10.0;
        modelRepository.setBuyIn(buyIn);

        int desiredProfit = 1000;
        modelRepository.setDesiredProfit(desiredProfit);

        BackingWithoutStudy mesh = new BackingWithoutStudy();
        modelRepository.setMesh(mesh);

        profitCalc = new ProfitCalc(modelRepository);
        double rollback = profitCalc.rollbackPercent(mesh, buyIn, desiredProfit / buyIn);

        Assertions.assertEquals(85, rollback);
    }

    @Test
    void dollarEVPerTourney() {
        modelRepository = new ModelRepository();

        double buyIn = 10.0;
        modelRepository.setBuyIn(buyIn);

        int chipsEV = 56;
        modelRepository.setExpChipsEV(chipsEV);

        double winCoefficient = 1.7280079;
        modelRepository.setWinCoefficient(winCoefficient);

        double loseCoefficient = -0.9927421;
        modelRepository.setLoseCoefficient(loseCoefficient);

        profitCalc = new ProfitCalc(modelRepository);
        double dollarEVPerTourney = profitCalc.dollarEVPerTourney(buyIn, chipsEV, winCoefficient, loseCoefficient);

        Assertions.assertEquals(0.15749233333333335, dollarEVPerTourney);
    }

}