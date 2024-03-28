package team.firestorm.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team.firestorm.repository.Model;

@Service
@AllArgsConstructor
public class EV {
    private final Model model;

    public double hyperEV() {
        double buyIn = this.model.getBuyIn();
        int tournaments = this.model.getTourneyPerPeriod();
        double chipsEV = this.model.getChipsEVFromTourney();
        double winCoefficient = this.model.getWinCoefficient();
        double loseCoefficient = this.model.getLoseCoefficient();

        return buyIn * tournaments * (((500 + chipsEV) / 1500) * winCoefficient
                + (1 - ((500 + chipsEV) / 1500)) * loseCoefficient);
    }

    public double dollarEVTotal() {
        return this.model.getDollarsPerHour() * this.model.getHoursPerDay() * this.model.getDaysPerMonth();
    }

    public double evBI() {
        return dollarEVTotal() / this.model.getBuyIn();
    }

    public double dollarEVTotalFromTourney() {
        return this.model.getBuyIn() * this.model.getRake() * this.model.getRakeBackTotal()
                + this.model.getDollarsEVPerTourney();
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

    public double dollarEVTotalPerTourney() {
        return this.model.getBuyIn() * this.model.getRake() * rakeBackPercentTotal() + this.model.getDollarsEVPerTourney();
    }
}
