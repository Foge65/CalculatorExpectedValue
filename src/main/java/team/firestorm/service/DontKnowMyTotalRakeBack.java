package team.firestorm.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team.firestorm.repository.Model;

@Service
@AllArgsConstructor
public class DontKnowMyTotalRakeBack {
    private final Model model;

    public double dollarEVTotalPerTourney() {
        return this.model.getBuyIn() * this.model.getRake() * this.model.getRakeBackTotal()
                + this.model.getDollarsEVPerTourney();
    }

    public double dollarEVTotal() {
        return tourneyPerPeriod() * dollarEVTotalPerTourney();
    }

    public double evBI() {
        return 0;
    }

    public double rakeBackPercentPerDay() {
        return this.model.getRakeBackDollarsPerDay() / (this.model.getTourneyPerDay() * this.model.getBuyIn() * this.model.getRake());
    }

    public int tourneyPerWeek() {
        return this.model.getTourneyPerDay() * this.model.getDaysPerWeek();
    }

    public double rakeBackPercentPerWeek() {
        return this.model.getRakeBackDollarsPerWeek() / (this.model.getBuyIn() * this.model.getRake() * tourneyPerWeek());
    }

    public int tourneyPerPeriod() {
        return tourneyPerWeek() * this.model.getWeeksPerPeriod();
    }

    public double rakeBackPercentPerPeriod() {
        return this.model.getRakeBackDollarsPerPeriod() / (tourneyPerPeriod() * this.model.getBuyIn() * this.model.getRake());
    }

    public double rakeBackPercentTotal() {
        return rakeBackPercentPerDay() + rakeBackPercentPerWeek() + rakeBackPercentPerPeriod();
    }
}
