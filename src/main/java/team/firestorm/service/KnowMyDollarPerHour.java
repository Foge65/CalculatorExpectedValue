package team.firestorm.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team.firestorm.repository.Model;

@Service
@AllArgsConstructor
public class KnowMyDollarPerHour {
    private final Model model;

    public double dollarEVTotal() {
        return this.model.getDollarsPerHour() * this.model.getHoursPerDay() * this.model.getDaysPerMonth();
    }

    public double evBI() {
        return dollarEVTotal() / this.model.getBuyIn();
    }
}
