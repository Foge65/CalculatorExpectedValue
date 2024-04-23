package team.firestorm.service.table;

import org.springframework.stereotype.Service;
import team.firestorm.repository.ModelRepository;

@Service
public class DontKnowMyTotalRakeBack extends ResultFiledServiceBase implements ResultFiledService {
    private final ModelRepository modelRepository;

    public DontKnowMyTotalRakeBack(ModelRepository modelRepository) {
        super(modelRepository);
        this.modelRepository = modelRepository;
    }

    public double rakeBackPercentPerDay() {
        return this.modelRepository.getRakeBackDollarsPerDay() /
                (this.modelRepository.getBuyIn() * this.modelRepository.getRake() / 100 * this.modelRepository.getTourneyPerDay()) * 100;
    }

    public int tourneyPerWeek() {
        return this.modelRepository.getTourneyPerDay() * this.modelRepository.getDaysPerWeek();
    }

    public double rakeBackPercentPerWeek() {
        return this.modelRepository.getRakeBackDollarsPerWeek() /
                (this.modelRepository.getBuyIn() * this.modelRepository.getRake() / 100 * tourneyPerWeek()) * 100;
    }

    public int tourneyPerPeriod() {
        return tourneyPerWeek() * this.modelRepository.getWeeksPerPeriod();
    }

    public double rakeBackPercentPerPeriod() {
        return this.modelRepository.getRakeBackDollarsPerPeriod()
                / (this.modelRepository.getBuyIn() * this.modelRepository.getRake() / 100 * tourneyPerPeriod()) * 100;
    }

    public double rakeBackPercentTotal() {
        return rakeBackPercentPerDay() + rakeBackPercentPerWeek() + rakeBackPercentPerPeriod();
    }

    public double dollarEVPerTourneyTotal() {
        return this.modelRepository.getBuyIn() * this.modelRepository.getRake() / 100 * rakeBackPercentTotal() / 100
                + dollarEVPerTourney();
    }

    @Override
    public double dollarEVTotal() {
        return tourneyPerPeriod() * dollarEVPerTourneyTotal();
    }
}
