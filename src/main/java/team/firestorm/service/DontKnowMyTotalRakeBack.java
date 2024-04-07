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

    public int rollbackPercent() {
        double evBI = evBI();
        int rollback = 0;
        if (evBI >= 225) {
            rollback = 80;
        } else if (evBI >= 190 && evBI <= 224) {
            rollback = 79;
        } else if (evBI >= 176 && evBI <= 189) {
            rollback = 77;
        } else if (evBI >= 150 && evBI <= 175) {
            rollback = 76;
        } else if (evBI >= 120 && evBI <= 149) {
            rollback = 70;
        } else if (evBI >= 90 && evBI <= 119) {
            rollback = 68;
        } else if (evBI >= 66 && evBI <= 89) {
            rollback = 61;
        } else if (evBI <= 65) {
            rollback = 25;
        }
        return rollback;
    }

    public double rollbackDollar() {
        return dollarEVTotal() * rollbackPercent() / 100;
    }
}
