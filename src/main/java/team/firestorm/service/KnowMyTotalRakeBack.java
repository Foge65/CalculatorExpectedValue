package team.firestorm.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team.firestorm.repository.Model;

@Service
@AllArgsConstructor
public class KnowMyTotalRakeBack {
    private final Model model;

    public double dollarEVTotalPerTourney() {
        return this.model.getBuyIn() * this.model.getRake() * this.model.getRakeBackTotal()
                + this.model.getDollarsEVPerTourney();
    }

    public double dollarEVTotal() {
        return dollarEVTotalPerTourney() * this.model.getTourneyPerPeriod();
    }

    public double evBI() {
        return 0;
    }
}
