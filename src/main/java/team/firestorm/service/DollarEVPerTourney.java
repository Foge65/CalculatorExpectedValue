package team.firestorm.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team.firestorm.repository.Model;

@Service
@AllArgsConstructor
public class DollarEVPerTourney {
    private final Model model;

    public double dollarEVPerTourney() {
        double buyIn = this.model.getBuyIn();
        double chipsEV = this.model.getChipsEVFromTourney();
        double winCoefficient = this.model.getWinCoefficient();
        double loseCoefficient = this.model.getLoseCoefficient();

        return buyIn * 1 * (((500 + chipsEV) / 1500) * winCoefficient
                + (1 - ((500 + chipsEV) / 1500)) * loseCoefficient);
    }
}
