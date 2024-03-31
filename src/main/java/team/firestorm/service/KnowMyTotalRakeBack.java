package team.firestorm.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team.firestorm.repository.Model;

@Service
@AllArgsConstructor
public class KnowMyTotalRakeBack {
    private final Model model;

    public double dollarEVTotalPerTourney() {
        return this.model.getBuyIn() * this.model.getRake() / 100 * this.model.getRakeBackTotal() / 100
                + this.model.getDollarsEVPerTourney();
    }

    public double dollarEVTotal() {
        return dollarEVTotalPerTourney() * this.model.getTourneyPerPeriod();
    }

    public double evBI() {
        return dollarEVTotal() / this.model.getBuyIn();
    }
}
