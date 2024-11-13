package team.firestorm.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.firestorm.repository.ModelRepository;
import team.firestorm.service.mesh.BackingWithStudy;
import team.firestorm.service.mesh.BackingWithoutStudy;
import team.firestorm.service.mesh.Mesh;
import team.firestorm.service.mesh.StudyWithoutBacking;

@Service
@RequiredArgsConstructor
public class ProfitCalc {
    private final ModelRepository modelRepository;
    private final Mesh mesh;

    public double requiredTourneys1() {
        double desiredProfit = modelRepository.getDesiredProfit();
        double buyIn = modelRepository.getBuyIn1();

        double rollback = modelRepository.getRollback1();

        double rake = modelRepository.getRake1();

        double rakeback = modelRepository.getRakebackPercent1();

        double dollarEVPerTourney = dollarEVPerTourney1();

        double requiredTourneys = desiredProfit / (rollback / 100) / (buyIn * (rake / 100) * (rakeback / 100) + dollarEVPerTourney) + 1;

        modelRepository.setRequiredTourneys(requiredTourneys);

        return requiredTourneys;
    }

    public double requiredTourneys2() {
        double desiredProfit = modelRepository.getDesiredProfit();
        double buyIn = modelRepository.getBuyIn2();

        double rollback = modelRepository.getRollback2();

        double rake = modelRepository.getRake2();

        double rakeback = modelRepository.getRakebackPercent2();

        double dollarEVPerTourney = dollarEVPerTourney2();

        double requiredTourneys = desiredProfit / (rollback / 100) / (buyIn * (rake / 100) * (rakeback / 100) + dollarEVPerTourney) + 1;

        modelRepository.setRequiredTourneys(requiredTourneys);

        return requiredTourneys;
    }

    public double rollbackPercent1() {
        double buyIn = modelRepository.getBuyIn1();
        double evBI = modelRepository.getDesiredProfit() / buyIn;

        int rollback = 0;

        if (mesh instanceof BackingWithStudy) {
            if (buyIn < 5) {
                if (evBI >= 225) rollback = 60;
                if (evBI >= 199 && evBI <= 224) rollback = 59;
                if (evBI >= 176 && evBI <= 189) rollback = 57;
                if (evBI >= 150 && evBI <= 175) rollback = 56;
                if (evBI >= 120 && evBI <= 149) rollback = 50;
                if (evBI >= 90 && evBI <= 119) rollback = 48;
                if (evBI >= 66 && evBI <= 89) rollback = 41;
                if (evBI >= 40 && evBI <= 65) rollback = 40;
                if (evBI < 40) rollback = 40;
            }
            if (buyIn >= 5 && buyIn < 10) {
                if (evBI >= 225) rollback = 62;
                if (evBI >= 199 && evBI <= 224) rollback = 61;
                if (evBI >= 176 && evBI <= 189) rollback = 59;
                if (evBI >= 150 && evBI <= 175) rollback = 56;
                if (evBI >= 120 && evBI <= 149) rollback = 55;
                if (evBI >= 90 && evBI <= 119) rollback = 51;
                if (evBI >= 66 && evBI <= 89) rollback = 46;
                if (evBI >= 40 && evBI <= 65) rollback = 42;
                if (evBI < 40) rollback = 40;
            }
            if (buyIn >= 10 && buyIn < 25) {
                if (evBI >= 225) rollback = 65;
                if (evBI >= 199 && evBI <= 224) rollback = 64;
                if (evBI >= 176 && evBI <= 189) rollback = 62;
                if (evBI >= 150 && evBI <= 175) rollback = 61;
                if (evBI >= 120 && evBI <= 149) rollback = 55;
                if (evBI >= 90 && evBI <= 119) rollback = 53;
                if (evBI >= 66 && evBI <= 89) rollback = 46;
                if (evBI >= 40 && evBI <= 65) rollback = 42;
                if (evBI < 40) rollback = 40;
            }
            if (buyIn >= 25 && buyIn < 50) {
                if (evBI >= 225) rollback = 70;
                if (evBI >= 199 && evBI <= 224) rollback = 69;
                if (evBI >= 176 && evBI <= 189) rollback = 67;
                if (evBI >= 150 && evBI <= 175) rollback = 64;
                if (evBI >= 120 && evBI <= 149) rollback = 60;
                if (evBI >= 90 && evBI <= 119) rollback = 58;
                if (evBI >= 66 && evBI <= 89) rollback = 51;
                if (evBI >= 40 && evBI <= 65) rollback = 48;
                if (evBI < 40) rollback = 45;
            }
            if (buyIn >= 50 && buyIn < 100) {
                if (evBI >= 225) rollback = 80;
                if (evBI >= 199 && evBI <= 224) rollback = 79;
                if (evBI >= 176 && evBI <= 189) rollback = 77;
                if (evBI >= 150 && evBI <= 175) rollback = 74;
                if (evBI >= 120 && evBI <= 149) rollback = 70;
                if (evBI >= 90 && evBI <= 119) rollback = 68;
                if (evBI >= 66 && evBI <= 89) rollback = 61;
                if (evBI >= 40 && evBI <= 65) rollback = 55;
                if (evBI < 40) rollback = 50;
            }
            if (buyIn >= 100) {
                if (evBI >= 225) rollback = 83;
                if (evBI >= 199 && evBI <= 224) rollback = 82;
                if (evBI >= 176 && evBI <= 189) rollback = 80;
                if (evBI >= 150 && evBI <= 175) rollback = 76;
                if (evBI >= 120 && evBI <= 149) rollback = 75;
                if (evBI >= 90 && evBI <= 119) rollback = 73;
                if (evBI >= 66 && evBI <= 89) rollback = 66;
                if (evBI >= 40 && evBI <= 65) rollback = 60;
                if (evBI < 40) rollback = 60;
            }
        }

        if (mesh instanceof BackingWithoutStudy) {
            if (buyIn >= 5 && buyIn < 10) {
                if (evBI >= 280) rollback = 90;
                if (evBI >= 210 && evBI <= 279) rollback = 87;
                if (evBI >= 180 && evBI <= 209) rollback = 86;
                if (evBI >= 155 && evBI <= 179) rollback = 85;
                if (evBI >= 125 && evBI <= 154) rollback = 84;
                if (evBI >= 95 && evBI <= 124) rollback = 82;
                if (evBI >= 70 && evBI <= 89) rollback = 80;
                if (evBI >= 55 && evBI <= 69) rollback = 78;
                if (evBI < 55) rollback = 12;
            }
            if (buyIn >= 10 && buyIn < 25) {
                if (evBI >= 280) rollback = 95;
                if (evBI >= 195 && evBI <= 279) rollback = 93;
                if (evBI >= 145 && evBI <= 194) rollback = 91;
                if (evBI >= 105 && evBI <= 144) rollback = 88;
                if (evBI >= 80 && evBI <= 104) rollback = 85;
                if (evBI >= 65 && evBI <= 79) rollback = 82;
                if (evBI >= 50 && evBI <= 64) rollback = 79;
                if (evBI >= 40 && evBI <= 49) rollback = 76;
                if (evBI < 40) rollback = 8;
            }
            if (buyIn >= 25) {
                if (evBI >= 240) rollback = 95;
                if (evBI >= 190 && evBI <= 239) rollback = 94;
                if (evBI >= 140 && evBI <= 189) rollback = 92;
                if (evBI >= 120 && evBI <= 139) rollback = 91;
                if (evBI >= 105 && evBI <= 119) rollback = 90;
                if (evBI >= 85 && evBI <= 104) rollback = 88;
                if (evBI >= 70 && evBI <= 84) rollback = 86;
                if (evBI >= 50 && evBI <= 69) rollback = 85;
                if (evBI < 50) rollback = 6;
            }
        }

        if (mesh instanceof StudyWithoutBacking) {
            if (buyIn < 5) {
                if (evBI >= 225) rollback = 80;
                if (evBI >= 190 && evBI <= 224) rollback = 79;
                if (evBI >= 176 && evBI <= 189) rollback = 77;
                if (evBI >= 150 && evBI <= 175) rollback = 76;
                if (evBI >= 120 && evBI <= 149) rollback = 70;
                if (evBI >= 90 && evBI <= 119) rollback = 68;
                if (evBI >= 66 && evBI <= 89) rollback = 61;
                if (evBI <= 65) rollback = 25;
            }
            if (buyIn >= 5 && buyIn < 10) {
                if (evBI >= 225) rollback = 82;
                if (evBI >= 190 && evBI <= 224) rollback = 81;
                if (evBI >= 176 && evBI <= 189) rollback = 79;
                if (evBI >= 150 && evBI <= 175) rollback = 76;
                if (evBI >= 120 && evBI <= 149) rollback = 75;
                if (evBI >= 90 && evBI <= 119) rollback = 71;
                if (evBI >= 66 && evBI <= 89) rollback = 66;
                if (evBI >= 56 && evBI <= 65) rollback = 64;
                if (evBI <= 55) rollback = 20;
            }
            if (buyIn >= 10) {
                if (evBI >= 225) rollback = 85;
                if (evBI >= 190 && evBI <= 224) rollback = 84;
                if (evBI >= 176 && evBI <= 189) rollback = 82;
                if (evBI >= 150 && evBI <= 175) rollback = 81;
                if (evBI >= 120 && evBI <= 149) rollback = 75;
                if (evBI >= 90 && evBI <= 119) rollback = 73;
                if (evBI >= 66 && evBI <= 89) rollback = 66;
                if (evBI >= 56 && evBI <= 65) rollback = 62;
                if (evBI <= 55) rollback = 20;
            }
        }

        modelRepository.setRollback1(rollback);

        return rollback;
    }

    public double rollbackPercent2() {
        double buyIn = modelRepository.getBuyIn2();
        double evBI = modelRepository.getDesiredProfit() / buyIn;

        int rollback = 0;

        if (mesh instanceof BackingWithStudy) {
            if (buyIn < 5) {
                if (evBI >= 225) rollback = 60;
                if (evBI >= 199 && evBI <= 224) rollback = 59;
                if (evBI >= 176 && evBI <= 189) rollback = 57;
                if (evBI >= 150 && evBI <= 175) rollback = 56;
                if (evBI >= 120 && evBI <= 149) rollback = 50;
                if (evBI >= 90 && evBI <= 119) rollback = 48;
                if (evBI >= 66 && evBI <= 89) rollback = 41;
                if (evBI >= 40 && evBI <= 65) rollback = 40;
                if (evBI < 40) rollback = 40;
            }
            if (buyIn >= 5 && buyIn < 10) {
                if (evBI >= 225) rollback = 62;
                if (evBI >= 199 && evBI <= 224) rollback = 61;
                if (evBI >= 176 && evBI <= 189) rollback = 59;
                if (evBI >= 150 && evBI <= 175) rollback = 56;
                if (evBI >= 120 && evBI <= 149) rollback = 55;
                if (evBI >= 90 && evBI <= 119) rollback = 51;
                if (evBI >= 66 && evBI <= 89) rollback = 46;
                if (evBI >= 40 && evBI <= 65) rollback = 42;
                if (evBI < 40) rollback = 40;
            }
            if (buyIn >= 10 && buyIn < 25) {
                if (evBI >= 225) rollback = 65;
                if (evBI >= 199 && evBI <= 224) rollback = 64;
                if (evBI >= 176 && evBI <= 189) rollback = 62;
                if (evBI >= 150 && evBI <= 175) rollback = 61;
                if (evBI >= 120 && evBI <= 149) rollback = 55;
                if (evBI >= 90 && evBI <= 119) rollback = 53;
                if (evBI >= 66 && evBI <= 89) rollback = 46;
                if (evBI >= 40 && evBI <= 65) rollback = 42;
                if (evBI < 40) rollback = 40;
            }
            if (buyIn >= 25 && buyIn < 50) {
                if (evBI >= 225) rollback = 70;
                if (evBI >= 199 && evBI <= 224) rollback = 69;
                if (evBI >= 176 && evBI <= 189) rollback = 67;
                if (evBI >= 150 && evBI <= 175) rollback = 64;
                if (evBI >= 120 && evBI <= 149) rollback = 60;
                if (evBI >= 90 && evBI <= 119) rollback = 58;
                if (evBI >= 66 && evBI <= 89) rollback = 51;
                if (evBI >= 40 && evBI <= 65) rollback = 48;
                if (evBI < 40) rollback = 45;
            }
            if (buyIn >= 50 && buyIn < 100) {
                if (evBI >= 225) rollback = 80;
                if (evBI >= 199 && evBI <= 224) rollback = 79;
                if (evBI >= 176 && evBI <= 189) rollback = 77;
                if (evBI >= 150 && evBI <= 175) rollback = 74;
                if (evBI >= 120 && evBI <= 149) rollback = 70;
                if (evBI >= 90 && evBI <= 119) rollback = 68;
                if (evBI >= 66 && evBI <= 89) rollback = 61;
                if (evBI >= 40 && evBI <= 65) rollback = 55;
                if (evBI < 40) rollback = 50;
            }
            if (buyIn >= 100) {
                if (evBI >= 225) rollback = 83;
                if (evBI >= 199 && evBI <= 224) rollback = 82;
                if (evBI >= 176 && evBI <= 189) rollback = 80;
                if (evBI >= 150 && evBI <= 175) rollback = 76;
                if (evBI >= 120 && evBI <= 149) rollback = 75;
                if (evBI >= 90 && evBI <= 119) rollback = 73;
                if (evBI >= 66 && evBI <= 89) rollback = 66;
                if (evBI >= 40 && evBI <= 65) rollback = 60;
                if (evBI < 40) rollback = 60;
            }
        }

        if (mesh instanceof BackingWithoutStudy) {
            if (buyIn >= 5 && buyIn < 10) {
                if (evBI >= 280) rollback = 90;
                if (evBI >= 210 && evBI <= 279) rollback = 87;
                if (evBI >= 180 && evBI <= 209) rollback = 86;
                if (evBI >= 155 && evBI <= 179) rollback = 85;
                if (evBI >= 125 && evBI <= 154) rollback = 84;
                if (evBI >= 95 && evBI <= 124) rollback = 82;
                if (evBI >= 70 && evBI <= 89) rollback = 80;
                if (evBI >= 55 && evBI <= 69) rollback = 78;
                if (evBI < 55) rollback = 12;
            }
            if (buyIn >= 10 && buyIn < 25) {
                if (evBI >= 280) rollback = 95;
                if (evBI >= 195 && evBI <= 279) rollback = 93;
                if (evBI >= 145 && evBI <= 194) rollback = 91;
                if (evBI >= 105 && evBI <= 144) rollback = 88;
                if (evBI >= 80 && evBI <= 104) rollback = 85;
                if (evBI >= 65 && evBI <= 79) rollback = 82;
                if (evBI >= 50 && evBI <= 64) rollback = 79;
                if (evBI >= 40 && evBI <= 49) rollback = 76;
                if (evBI < 40) rollback = 8;
            }
            if (buyIn >= 25) {
                if (evBI >= 240) rollback = 95;
                if (evBI >= 190 && evBI <= 239) rollback = 94;
                if (evBI >= 140 && evBI <= 189) rollback = 92;
                if (evBI >= 120 && evBI <= 139) rollback = 91;
                if (evBI >= 105 && evBI <= 119) rollback = 90;
                if (evBI >= 85 && evBI <= 104) rollback = 88;
                if (evBI >= 70 && evBI <= 84) rollback = 86;
                if (evBI >= 50 && evBI <= 69) rollback = 85;
                if (evBI < 50) rollback = 6;
            }
        }

        if (mesh instanceof StudyWithoutBacking) {
            if (buyIn < 5) {
                if (evBI >= 225) rollback = 80;
                if (evBI >= 190 && evBI <= 224) rollback = 79;
                if (evBI >= 176 && evBI <= 189) rollback = 77;
                if (evBI >= 150 && evBI <= 175) rollback = 76;
                if (evBI >= 120 && evBI <= 149) rollback = 70;
                if (evBI >= 90 && evBI <= 119) rollback = 68;
                if (evBI >= 66 && evBI <= 89) rollback = 61;
                if (evBI <= 65) rollback = 25;
            }
            if (buyIn >= 5 && buyIn < 10) {
                if (evBI >= 225) rollback = 82;
                if (evBI >= 190 && evBI <= 224) rollback = 81;
                if (evBI >= 176 && evBI <= 189) rollback = 79;
                if (evBI >= 150 && evBI <= 175) rollback = 76;
                if (evBI >= 120 && evBI <= 149) rollback = 75;
                if (evBI >= 90 && evBI <= 119) rollback = 71;
                if (evBI >= 66 && evBI <= 89) rollback = 66;
                if (evBI >= 56 && evBI <= 65) rollback = 64;
                if (evBI <= 55) rollback = 20;
            }
            if (buyIn >= 10) {
                if (evBI >= 225) rollback = 85;
                if (evBI >= 190 && evBI <= 224) rollback = 84;
                if (evBI >= 176 && evBI <= 189) rollback = 82;
                if (evBI >= 150 && evBI <= 175) rollback = 81;
                if (evBI >= 120 && evBI <= 149) rollback = 75;
                if (evBI >= 90 && evBI <= 119) rollback = 73;
                if (evBI >= 66 && evBI <= 89) rollback = 66;
                if (evBI >= 56 && evBI <= 65) rollback = 62;
                if (evBI <= 55) rollback = 20;
            }
        }

        modelRepository.setRollback2(rollback);

        return rollback;
    }

    public double dollarEVPerTourney1() {
        double buyIn = modelRepository.getBuyIn1();
        double chipsEV = modelRepository.getExpChipsT1();
        double winCoefficient = modelRepository.getWinCoefficient1();
        double loseCoefficient = modelRepository.getLoseCoefficient1();

        double expEVT = buyIn * 1 * (((500 + chipsEV) / 1500) * winCoefficient
                                     + (1 - ((500 + chipsEV) / 1500)) * loseCoefficient);

        modelRepository.setExpEVT1(expEVT);

        return expEVT;
    }

    public double dollarEVPerTourney2() {
        double buyIn = modelRepository.getBuyIn2();
        double chipsEV = modelRepository.getExpChipsT2();
        double winCoefficient = modelRepository.getWinCoefficient2();
        double loseCoefficient = modelRepository.getLoseCoefficient2();

        double expEVT = buyIn * 1 * (((500 + chipsEV) / 1500) * winCoefficient
                                     + (1 - ((500 + chipsEV) / 1500)) * loseCoefficient);

        modelRepository.setExpEVT2(expEVT);

        return expEVT;
    }

    public double requiredHours() {
        double requiredHours = modelRepository.getRequiredTourneys() / (modelRepository.getTables1() * modelRepository.getTourneysPerTable1());

        modelRepository.setRequiredHours(requiredHours);

        return requiredHours;
    }

    public double dollarsPerHour1() {
        return modelRepository.getDesiredProfit() / modelRepository.getRequiredHours();
    }

    public double tourneys() {
        double tourneys = modelRepository.getTables2() * modelRepository.getTourneysPerTable2() * modelRepository.getHaveHours();

        modelRepository.setTourneys(tourneys);

        return tourneys;
    }

    public double estimatedExpectation() {
        double exp = (modelRepository.getExpEVT2() + modelRepository.getBuyIn2() * (modelRepository.getRake2() / 100)
                                                     * (modelRepository.getRakebackPercent2() / 100))
                     * modelRepository.getTables2() * modelRepository.getTourneysPerTable2() * modelRepository.getHaveHours();

        modelRepository.setEstimatedExpectation(exp);

        return exp;
    }

    public double dollarsPerHour2() {
        return modelRepository.getEstimatedExpectation() / modelRepository.getHaveHours();
    }

    public double profitAfterRollback1() {
        double profit = modelRepository.getEstimatedExpectation() * modelRepository.getRollback1();
        modelRepository.setProfitAfterRollback(profit);
        return profit;
    }

    public double profitAfterRollback2() {
        double profit = modelRepository.getEstimatedExpectation() * modelRepository.getRollback2();
        modelRepository.setProfitAfterRollback(profit);
        return profit;
    }
}
