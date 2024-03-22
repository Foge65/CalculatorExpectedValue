package team.firestorm.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team.firestorm.repository.Model;

@Service
@AllArgsConstructor
public class HyperEV {
    private final Model model;

    public double hyperEV() {
        double buyIn = model.getBuyIn();
        int tournaments = model.getTournaments();
        double chipsEV = model.getChipsEV();
        double winCoefficient = model.getWinCoefficient();
        double loseCoefficient = model.getLoseCoefficient();

        return buyIn * tournaments * (((500 + chipsEV) / 1500) * winCoefficient
                + (1 - ((500 + chipsEV) / 1500)) * loseCoefficient);
    }

    public double evTotal() {
        return model.getDollarsPerHour() * model.getHoursPerDay() * model.getDaysPerMonth();
    }

    public double profitTotal() {
        return hyperEV() + (model.getTournaments() * model.getBuyIn()
                * model.getRake() / 100 * model.getRakeBack() / 100);
    }
}
