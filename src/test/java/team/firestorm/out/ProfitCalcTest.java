package team.firestorm.out;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import team.firestorm.domain.ProfitCalc;
import team.firestorm.repository.ModelRepository;
import team.firestorm.service.mesh.BackingWithoutStudy;
import team.firestorm.service.mesh.Mesh;

class ProfitCalcTest {
    private ModelRepository modelRepository;
    private ProfitCalc profitCalc;
    private Mesh mesh;

    @Test
    void requiredTourneys() {
        modelRepository = new ModelRepository();

        modelRepository.setDesiredProfit(1000);
        modelRepository.setBuyIn(10.0);
        modelRepository.setRake(7.0);
        modelRepository.setMesh(new BackingWithoutStudy());
        modelRepository.setRakebackPercent(35.0);
        modelRepository.setExpChipsT(56);
        modelRepository.setWinCoefficient(1.7280079);
        modelRepository.setLoseCoefficient(-0.9927421);
        modelRepository.setRollback(85);

        mesh = new BackingWithoutStudy();

        profitCalc = new ProfitCalc(modelRepository, mesh);
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

        profitCalc = new ProfitCalc(modelRepository, mesh);
        double rollback = profitCalc.rollbackPercent();

        Assertions.assertEquals(85, rollback);
    }

    @Test
    void dollarEVPerTourney() {
        modelRepository = new ModelRepository();

        double buyIn = 10.0;
        modelRepository.setBuyIn(buyIn);

        double chipsEV = 56;
        modelRepository.setExpChipsT(chipsEV);

        double winCoefficient = 1.7280079;
        modelRepository.setWinCoefficient(winCoefficient);

        double loseCoefficient = -0.9927421;
        modelRepository.setLoseCoefficient(loseCoefficient);

        profitCalc = new ProfitCalc(modelRepository, mesh);
        double dollarEVPerTourney = profitCalc.dollarEVPerTourney();

        Assertions.assertEquals(0.15749233333333335, dollarEVPerTourney);
    }

}