package team.firestorm.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.firestorm.model.HaveHoursModel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@Getter
@RequiredArgsConstructor
public class HaveHoursModelService {
    private final Map<Integer, HaveHoursModel> models = new HashMap<>();

    public void addModel() {
        HaveHoursModel newModel = new HaveHoursModel();
        if (models.isEmpty()) {
            models.put(1, newModel);
            newModel.setHaveHours(1000);
            newModel.setExpChipsT(65);
            newModel.setTables(6);
            newModel.setRakebackPct(40);
        } else {
            int lastKey = Collections.max(models.keySet());
            models.put(lastKey + 1, newModel);
            HaveHoursModel lastModel = models.get(lastKey);
            lastModel.setHaveHours(lastModel.getHaveHours());
            lastModel.setRoom(lastModel.getRoom());
            lastModel.setBuyIn(lastModel.getBuyIn());
            lastModel.setExpChipsT(lastModel.getExpChipsT());
            lastModel.setTables(lastModel.getTables());
            lastModel.setRakebackPct(lastModel.getRakebackPct());
            lastModel.setMesh(lastModel.getMesh());
        }
    }

    public void removeModelById(int id) {
        if (!models.containsKey(id)) {
            throw new NoSuchElementException("Model with id " + id + " not found");
        }
        models.remove(id);
    }

    public HaveHoursModel findModelById(int id) {
        if (!models.containsKey(id)) {
            throw new NoSuchElementException("Model with id " + id + " not found");
        }
        return models.get(id);
    }

    public HaveHoursModel findLastModel() {
        int lastKey = Collections.max(models.keySet());
        return models.get(lastKey);
    }

    public int findLastId() {
        return Collections.max(models.keySet());
    }

}
