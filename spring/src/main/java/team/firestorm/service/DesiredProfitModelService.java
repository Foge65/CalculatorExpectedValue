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

    public DesiredProfitModel addModel() {
        DesiredProfitModel newModel = new DesiredProfitModel();
        if (models.isEmpty()) {
            models.put(1, newModel);
            newModel.setDesiredProfit(1000);
            newModel.setExpChipsT(65);
            newModel.setTables(6);
            newModel.setRakebackPct(40);
        } else {
            int lastKey = Collections.max(models.keySet());
            models.put(lastKey + 1, newModel);
            DesiredProfitModel lastModel = models.get(lastKey);
            initModel(lastModel, newModel);
        }
        return newModel;
    }

    private void initModel(DesiredProfitModel lastModel, DesiredProfitModel newModel) {
        newModel.setRoom(lastModel.getRoom());
        newModel.setBuyIn(lastModel.getBuyIn());
        newModel.setMesh(lastModel.getMesh());
        newModel.setDesiredProfit(lastModel.getDesiredProfit());
        newModel.setExpChipsT(lastModel.getExpChipsT());
        newModel.setTables(lastModel.getTables());
        newModel.setRakebackPct(lastModel.getRakebackPct());
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
