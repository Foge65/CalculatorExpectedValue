package team.firestorm.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import team.firestorm.repository.Model;

@SuperBuilder
@RequiredArgsConstructor
public abstract class ResultFiledServiceBase implements ResultFiledService {
    private final Model model;

    @Override
    public double evBI() {
        return dollarEVTotal() / this.model.getBuyIn();
    }
}
