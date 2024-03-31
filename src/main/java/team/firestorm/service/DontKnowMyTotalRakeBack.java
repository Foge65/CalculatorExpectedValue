package team.firestorm.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team.firestorm.repository.Model;

@Service
@AllArgsConstructor
public class DontKnowMyTotalRakeBack {
    private final Model model;
    private final HyperEV hyperEV;

    public double dollarEVPerTourney() {
        int tourney = tourneyPerPeriod();
        return hyperEV.hyperEV(tourney) / tourney;
    }

    public double rakeBackPercentPerDay() {
        return this.model.getRakeBackDollarsPerDay() /
                (this.model.getBuyIn() * this.model.getRake() / 100 * this.model.getTourneyPerDay()) * 100;
    }

    public int tourneyPerWeek() {
        return this.model.getTourneyPerDay() * this.model.getDaysPerWeek();
    }

    public double rakeBackPercentPerWeek() {
        return this.model.getRakeBackDollarsPerWeek() /
                (this.model.getBuyIn() * this.model.getRake() / 100 * tourneyPerWeek()) * 100;
    }

    public int tourneyPerPeriod() {
        return tourneyPerWeek() * this.model.getWeeksPerPeriod();
    }

    public double rakeBackPercentPerPeriod() {
        return this.model.getRakeBackDollarsPerPeriod()
                / (this.model.getBuyIn() * this.model.getRake() / 100 * tourneyPerPeriod()) * 100;
    }

    public double rakeBackPercentTotal() {
        return rakeBackPercentPerDay() + rakeBackPercentPerWeek() + rakeBackPercentPerPeriod();
    }

    public double dollarEVTotalPerTourney() {
        return this.model.getBuyIn() * this.model.getRake() / 100 * rakeBackPercentTotal() / 100
                + dollarEVPerTourney();
    }

    public double dollarEVTotal() {
        return tourneyPerPeriod() * dollarEVTotalPerTourney();
    }

    public double evBI() {
        return dollarEVTotal() / this.model.getBuyIn();
    }
}
