package team.firestorm.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.firestorm.model.DesiredProfitModel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@Getter
@RequiredArgsConstructor
public class DesiredProfitModelService {
    private final Map<Integer, DesiredProfitModel> models = new HashMap<>();

    public void addModel() {
        if (models.isEmpty()) {
            models.put(1, new DesiredProfitModel());
        } else {
            int lastKey = Collections.max(models.keySet());
            models.put(lastKey + 1, new DesiredProfitModel());
        }
    }

    public void removeModelById(int id) {
        if (!models.containsKey(id)) {
            throw new NoSuchElementException("Model with id " + id + " not found");
        }
        models.remove(id);
    }

    public DesiredProfitModel findModelById(int id) {
        if (!models.containsKey(id)) {
            throw new NoSuchElementException("Model with id " + id + " not found");
        }
        return models.get(id);
    }

    public DesiredProfitModel findLastModel() {
        int lastKey = Collections.max(models.keySet());
        return models.get(lastKey);
    }

    public int findLastId() {
        return Collections.max(models.keySet());
    }

}
