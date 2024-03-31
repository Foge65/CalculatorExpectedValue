package team.firestorm.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team.firestorm.repository.Model;

@Service
@AllArgsConstructor
public class HyperEV {
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
}
