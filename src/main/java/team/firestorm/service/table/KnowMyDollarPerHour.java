package team.firestorm.service.table;

import org.springframework.stereotype.Service;
import team.firestorm.repository.ModelRepository;

@Service
public class KnowMyDollarPerHour extends ResultFiledServiceBase implements ResultFiledService {
    private final ModelRepository modelRepository;

    public KnowMyDollarPerHour(ModelRepository modelRepository) {
        super(modelRepository);
        this.modelRepository = modelRepository;
    }

    @Override
    public double dollarEVPerTourneyTotal() {
        return 0;
    }

    @Override
    public double dollarEVTotal() {
        return this.modelRepository.getDollarsPerHour() * this.modelRepository.getHoursPerDay() * this.modelRepository.getDaysPerMonth();
    }
}
