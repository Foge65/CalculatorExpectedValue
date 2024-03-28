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
        int tournaments = this.model.getTournaments();
        double chipsEV = this.model.getChipsEV();
        double winCoefficient = this.model.getWinCoefficient();
        double loseCoefficient = this.model.getLoseCoefficient();

        return buyIn * tournaments * (((500 + chipsEV) / 1500) * winCoefficient
                + (1 - ((500 + chipsEV) / 1500)) * loseCoefficient);
    }

    public double evTotal() {
        return this.model.getDollarsPerHour() * this.model.getHoursPerDay() * this.model.getDaysPerMonth();
    }

    public double profitTotal() {
        return hyperEV() + (this.model.getTournaments() * this.model.getBuyIn()
                * this.model.getRake() / 100 * this.model.getRakeBack() / 100)
                + this.model.getOtherPayments();
    }

    public Double evBI() {
        return this.model.getEvTotal() / this.model.getBuyIn();
    }

    public Double profitTotalPerTourney() {
        return this.model.getBuyIn() * this.model.getRake() * this.model.getRakeBack()
                + this.model.getDollarsEVPerTourney();
    }
}
