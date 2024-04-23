package team.firestorm.service.table;

import org.springframework.stereotype.Service;
import team.firestorm.repository.ModelRepository;

@Service
public class KnowMyTotalRakeBack extends ResultFiledServiceBase implements ResultFiledService {
    private final ModelRepository modelRepository;

    public KnowMyTotalRakeBack(ModelRepository modelRepository) {
        super(modelRepository);
        this.modelRepository = modelRepository;
    }

    public double dollarEVPerTourneyTotal() {
        return this.modelRepository.getBuyIn() * this.modelRepository.getRake() / 100 * this.modelRepository.getRakeBackTotal() / 100
                + dollarEVPerTourney();
    }

    @Override
    public double dollarEVTotal() {
        return dollarEVPerTourneyTotal() * this.modelRepository.getTourneyPerPeriod();
    }
}
