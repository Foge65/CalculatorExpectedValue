package team.firestorm.service.table;

import org.springframework.stereotype.Service;
import team.firestorm.repository.Model;

@Service
public class KnowMyDollarPerHour extends ResultFiledServiceBase implements ResultFiledService {
    private final Model model;

    public KnowMyDollarPerHour(Model model) {
        super(model);
        this.model = model;
    }

    @Override
    public double dollarEVTotal() {
        return this.model.getDollarsPerHour() * this.model.getHoursPerDay() * this.model.getDaysPerMonth();
    }
}
