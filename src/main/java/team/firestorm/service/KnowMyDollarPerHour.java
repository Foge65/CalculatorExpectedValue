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
