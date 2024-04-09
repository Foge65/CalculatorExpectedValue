package team.firestorm.service.table;

import org.springframework.stereotype.Service;
import team.firestorm.repository.Model;

@Service
public class KnowMyTotalRakeBack extends ResultFiledServiceBase implements ResultFiledService {
    private final Model model;

    public KnowMyTotalRakeBack(Model model) {
        super(model);
        this.model = model;
    }

    public double dollarEVTotalPerTourney() {
        return this.model.getBuyIn() * this.model.getRake() / 100 * this.model.getRakeBackTotal() / 100
                + dollarEVPerTourney();
    }

    @Override
    public double dollarEVTotal() {
        return dollarEVTotalPerTourney() * this.model.getTourneyPerPeriod();
    }
}
