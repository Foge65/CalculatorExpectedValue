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
        modelRepository.setBuyIn1(10.0);
        modelRepository.setRake1(7.0);
        modelRepository.setMesh1(new BackingWithoutStudy());
        modelRepository.setRakebackPercent1(35.0);
        modelRepository.setExpChipsT1(56);
        modelRepository.setWinCoefficient1(1.7280079);
        modelRepository.setLoseCoefficient1(-0.9927421);
        modelRepository.setRollback1(85);

        mesh = new BackingWithoutStudy();

        profitCalc = new ProfitCalc(modelRepository, mesh);
        double requiredTourneys = profitCalc.requiredTourneys1();

        Assertions.assertEquals(2923.9639692564597, requiredTourneys);
    }

    @Test
    void rollbackPercent() {
        modelRepository = new ModelRepository();

        double buyIn = 10.0;
        modelRepository.setBuyIn1(buyIn);

        int desiredProfit = 1000;
        modelRepository.setDesiredProfit(desiredProfit);

        BackingWithoutStudy mesh = new BackingWithoutStudy();
        modelRepository.setMesh1(mesh);

        profitCalc = new ProfitCalc(modelRepository, mesh);
        double rollback = profitCalc.rollbackPercent1();

        Assertions.assertEquals(85, rollback);
    }

    @Test
    void dollarEVPerTourney() {
        modelRepository = new ModelRepository();

        double buyIn = 10.0;
        modelRepository.setBuyIn1(buyIn);

        double chipsEV = 56;
        modelRepository.setExpChipsT1(chipsEV);

        double winCoefficient = 1.7280079;
        modelRepository.setWinCoefficient1(winCoefficient);

        double loseCoefficient = -0.9927421;
        modelRepository.setLoseCoefficient1(loseCoefficient);

        profitCalc = new ProfitCalc(modelRepository, mesh);
        double dollarEVPerTourney = profitCalc.dollarEVPerTourney1();

        Assertions.assertEquals(0.15749233333333335, dollarEVPerTourney);
    }

}