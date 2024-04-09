package team.firestorm.service;

import org.springframework.stereotype.Service;
import team.firestorm.repository.Model;

@Service
public class KnowMyTotalRakeBack extends ResultFiledServiceBase implements ResultFiledService {
    private final Model model;
    private final DollarEVPerTourney dollarEVPerTourney;

    public KnowMyTotalRakeBack(Model model, DollarEVPerTourney dollarEVPerTourney) {
        super(model);
        this.model = model;
        this.dollarEVPerTourney = dollarEVPerTourney;
    }

    public double dollarEVPerTourney() {
        return dollarEVPerTourney.dollarEVPerTourney();
    }

    public double dollarEVTotalPerTourney() {
        return this.model.getBuyIn() * this.model.getRake() / 100 * this.model.getRakeBackTotal() / 100
                + dollarEVPerTourney();
    }

    @Override
    public double dollarEVTotal() {
        return dollarEVTotalPerTourney() * this.model.getTourneyPerPeriod();
    }

    public double rollbackDollar() {
        return dollarEVTotal() * rollbackPercent() / 100;
    }
}
