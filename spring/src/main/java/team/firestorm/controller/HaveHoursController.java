package team.firestorm.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.firestorm.dto.HaveHoursRequestDTO;
import team.firestorm.model.HaveHoursModel;
import team.firestorm.service.HaveHoursModelService;
import team.firestorm.service.mesh.*;
import team.firestorm.service.room.*;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/haveHours")
@RequiredArgsConstructor
public class HaveHoursController {
    private final HaveHoursModelService modelService;
    private Room room;
    private Mesh mesh;

    @PostConstruct
    public void init() {
        createModel();
        HaveHoursModel model = modelService.findLastModel();
        initializeRoom(model);
        initializeMesh(model);
        initializeCalcFields(model);
    }

    @PostMapping("/createModel")
    public Map<Integer, HaveHoursModel> createModel() {
        modelService.createModel();
        HaveHoursModel model = modelService.findLastModel();
        initializeRoom(model);
        initializeMesh(model);
        initializeCalcFields(model);
        return modelService.getModels();
    }

    private void initializeRoom(HaveHoursModel model) {
        model.setRooms(Rooms.values());
        room = new PokerStars();
        propertiesRoom(model);

        HaveHoursRequestDTO requestDTO = new HaveHoursRequestDTO();
        requestDTO.setBuyIn(0.25);
        int id = modelService.findLastId();
        setBuyIn(id, requestDTO);
    }

    private void propertiesRoom(HaveHoursModel model) {
        model.setRoom(room);
        model.setBuyIns(room.buyIns());
        model.setRakes(room.rakes());
        model.setWinCoefficients(room.winCoefficient());
        model.setLoseCoefficients(room.loseCoefficient());
        model.setTourneysPerTable(room.tourneysPerTable());
    }

    private void initializeMesh(HaveHoursModel model) {
        model.setMeshes(Meshes.values());
        mesh = new ClearProfit();
        model.setMesh(mesh);
    }

    private void initializeCalcFields(HaveHoursModel model) {
        int id = modelService.findLastId();
        model.setExpEVT(getExpEVT(id));
        model.setRollbackPct(getRollback(id));
        model.setRequiredTourneys(getRequiredTourneys(id));
        model.setEstimatedExpectation(getEstimatedExpectation(id));
        model.setDollarsPerHour(getDollarPerHour(id));
    }

    @PostMapping("/removeModel")
    public Map<Integer, HaveHoursModel> removeModel(@RequestParam("id") int id) {
        modelService.removeModelById(id);
        return modelService.getModels();
    }

    @GetMapping("/getModel")
    public HaveHoursModel getModel(@RequestParam("id") int id) {
        HaveHoursModel model = modelService.getModels().get(id);
        if (model == null) {
            throw new NoSuchElementException("Model with id " + id + " not found");
        }
        return model;
    }

    @GetMapping("/getAllData")
    public Map<Integer, HaveHoursModel> getData() {
        return modelService.getModels();
    }

    @PostMapping("/setHaveHours")
    public HaveHoursModel setHaveHours(@RequestParam("id") int id, @RequestBody HaveHoursRequestDTO request) {
        HaveHoursModel model = getModel(id);
        model.setHaveHours(request.getHaveHours());
        calcFields(id);
        return model;
    }

    @PostMapping("/setTables")
    public HaveHoursModel setTables(@RequestParam("id") int id, @RequestBody HaveHoursRequestDTO request) {
        HaveHoursModel model = getModel(id);
        model.setTables(request.getTables());
        calcFields(id);
        return model;
    }

    @GetMapping("/getRooms")
    public Rooms[] getRooms(@RequestParam("id") int id) {
        return getModel(id).getRooms();
    }

    @GetMapping("/getRoom")
    public Room getRoom(@RequestParam("id") int id) {
        return getModel(id).getRoom();
    }

    @PostMapping("/setRoom")
    public HaveHoursModel setRoom(@RequestParam("id") int id, @RequestBody Map<String, String> request) {
        HaveHoursModel model = getModel(id);
        Rooms rooms = Rooms.valueOf(request.get("room"));
        createRoom(rooms);
        propertiesRoom(model);
        calcFields(id);
        return model;
    }

    private void createRoom(Rooms rooms) {
        switch (rooms) {
            case PokerStars -> new PokerStars();
            case Winamax -> new Winamax();
            case iPoker -> new IPoker();
        }
    }

    @PostMapping("/setBuyIn")
    public HaveHoursModel setBuyIn(@RequestParam("id") int id, @RequestBody HaveHoursRequestDTO request) {
        HaveHoursModel model = getModel(id);
        double buyIn = request.getBuyIn();
        model.setBuyIn(buyIn);

        int index = findIndexBySelectedCoefficient(room.buyIns(), buyIn);
        model.setRake(room.rakes()[index]);
        model.setWinCoefficient(room.winCoefficient()[index]);
        model.setLoseCoefficient(room.loseCoefficient()[index]);
        calcFields(id);
        return model;
    }

    private int findIndexBySelectedCoefficient(double[] buyIns, double buyIn) {
        return IntStream.range(0, buyIns.length).filter(i -> buyIns[i] == buyIn).findFirst().orElse(0);
    }

    @GetMapping("/getBuyIns")
    public double[] getBuyIns(@RequestParam("id") int id) {
        HaveHoursModel model = getModel(id);
        return model.getBuyIns();
    }

    @GetMapping("/getBuyIn")
    public double getBuyIn(@RequestParam("id") int id) {
        return getModel(id).getBuyIn();
    }

    @PostMapping("/setExpChipsT")
    public HaveHoursModel setExpChipsT(@RequestParam("id") int id, @RequestBody HaveHoursRequestDTO request) {
        HaveHoursModel model = getModel(id);
        model.setExpChipsT(request.getExpChipsT());
        calcFields(id);
        return model;
    }

    private double getExpEVT(int id) {
        HaveHoursModel model = getModel(id);
        double buyIn = model.getBuyIn();
        double chipsEV = model.getExpChipsT();
        double winCoefficient = model.getWinCoefficient();
        double loseCoefficient = model.getLoseCoefficient();

        double expEVT = buyIn * 1 * (((500 + chipsEV) / 1500) * winCoefficient
                                     + (1 - ((500 + chipsEV) / 1500)) * loseCoefficient);

        model.setExpEVT(expEVT);

        return expEVT;
    }

    @PostMapping("/setRakebackPct")
    public HaveHoursModel setRakebackPct(@RequestParam("id") int id, @RequestBody HaveHoursRequestDTO request) {
        HaveHoursModel model = getModel(id);
        model.setRakebackPct(request.getRakebackPct());
        calcFields(id);
        return model;
    }

    @GetMapping(("/getMeshes"))
    public Meshes[] getMeshes(@RequestParam("id") int id) {
        return getModel(id).getMeshes();
    }

    @GetMapping("/getMesh")
    public Mesh getMesh(@RequestParam("id") int id) {
        return getModel(id).getMesh();
    }

    @PostMapping("/setMesh")
    public HaveHoursModel setMesh(@RequestParam("id") int id, @RequestBody Map<String, String> request) {
        HaveHoursModel model = getModel(id);
        Meshes meshes = Meshes.valueOf(request.get("mesh"));

        initializeMesh(meshes);
        model.setMesh(mesh);

        calcFields(id);

        return model;
    }

    private void initializeMesh(Meshes meshes) {
        switch (meshes) {
            case ClearProfit -> mesh = new ClearProfit();
            case BackingWithStudy -> mesh = new BackingWithStudy();
            case BackingWithoutStudy -> mesh = new BackingWithoutStudy();
            case StudyWithoutBacking -> mesh = new StudyWithoutBacking();
        }
    }

    private double getRollback(int id) {
        HaveHoursModel model = getModel(id);
        double buyIn = model.getBuyIn();
        double evBI = model.getEstimatedExpectation() / buyIn;

        int rollback = 0;

        if (mesh instanceof BackingWithStudy) {
            if (buyIn < 5) {
                if (evBI >= 225) rollback = 60;
                if (evBI >= 199 && evBI <= 224) rollback = 59;
                if (evBI >= 176 && evBI <= 189) rollback = 57;
                if (evBI >= 150 && evBI <= 175) rollback = 56;
                if (evBI >= 120 && evBI <= 149) rollback = 50;
                if (evBI >= 90 && evBI <= 119) rollback = 48;
                if (evBI >= 66 && evBI <= 89) rollback = 41;
                if (evBI >= 40 && evBI <= 65) rollback = 40;
                if (evBI < 40) rollback = 40;
            }
            if (buyIn >= 5 && buyIn < 10) {
                if (evBI >= 225) rollback = 62;
                if (evBI >= 199 && evBI <= 224) rollback = 61;
                if (evBI >= 176 && evBI <= 189) rollback = 59;
                if (evBI >= 150 && evBI <= 175) rollback = 56;
                if (evBI >= 120 && evBI <= 149) rollback = 55;
                if (evBI >= 90 && evBI <= 119) rollback = 51;
                if (evBI >= 66 && evBI <= 89) rollback = 46;
                if (evBI >= 40 && evBI <= 65) rollback = 42;
                if (evBI < 40) rollback = 40;
            }
            if (buyIn >= 10 && buyIn < 25) {
                if (evBI >= 225) rollback = 65;
                if (evBI >= 199 && evBI <= 224) rollback = 64;
                if (evBI >= 176 && evBI <= 189) rollback = 62;
                if (evBI >= 150 && evBI <= 175) rollback = 61;
                if (evBI >= 120 && evBI <= 149) rollback = 55;
                if (evBI >= 90 && evBI <= 119) rollback = 53;
                if (evBI >= 66 && evBI <= 89) rollback = 46;
                if (evBI >= 40 && evBI <= 65) rollback = 42;
                if (evBI < 40) rollback = 40;
            }
            if (buyIn >= 25 && buyIn < 50) {
                if (evBI >= 225) rollback = 70;
                if (evBI >= 199 && evBI <= 224) rollback = 69;
                if (evBI >= 176 && evBI <= 189) rollback = 67;
                if (evBI >= 150 && evBI <= 175) rollback = 64;
                if (evBI >= 120 && evBI <= 149) rollback = 60;
                if (evBI >= 90 && evBI <= 119) rollback = 58;
                if (evBI >= 66 && evBI <= 89) rollback = 51;
                if (evBI >= 40 && evBI <= 65) rollback = 48;
                if (evBI < 40) rollback = 45;
            }
            if (buyIn >= 50 && buyIn < 100) {
                if (evBI >= 225) rollback = 80;
                if (evBI >= 199 && evBI <= 224) rollback = 79;
                if (evBI >= 176 && evBI <= 189) rollback = 77;
                if (evBI >= 150 && evBI <= 175) rollback = 74;
                if (evBI >= 120 && evBI <= 149) rollback = 70;
                if (evBI >= 90 && evBI <= 119) rollback = 68;
                if (evBI >= 66 && evBI <= 89) rollback = 61;
                if (evBI >= 40 && evBI <= 65) rollback = 55;
                if (evBI < 40) rollback = 50;
            }
            if (buyIn >= 100) {
                if (evBI >= 225) rollback = 83;
                if (evBI >= 199 && evBI <= 224) rollback = 82;
                if (evBI >= 176 && evBI <= 189) rollback = 80;
                if (evBI >= 150 && evBI <= 175) rollback = 76;
                if (evBI >= 120 && evBI <= 149) rollback = 75;
                if (evBI >= 90 && evBI <= 119) rollback = 73;
                if (evBI >= 66 && evBI <= 89) rollback = 66;
                if (evBI >= 40 && evBI <= 65) rollback = 60;
                if (evBI < 40) rollback = 60;
            }
        }

        if (mesh instanceof BackingWithoutStudy) {
            if (buyIn >= 5 && buyIn < 10) {
                if (evBI >= 280) rollback = 90;
                if (evBI >= 210 && evBI <= 279) rollback = 87;
                if (evBI >= 180 && evBI <= 209) rollback = 86;
                if (evBI >= 155 && evBI <= 179) rollback = 85;
                if (evBI >= 125 && evBI <= 154) rollback = 84;
                if (evBI >= 95 && evBI <= 124) rollback = 82;
                if (evBI >= 70 && evBI <= 89) rollback = 80;
                if (evBI >= 55 && evBI <= 69) rollback = 78;
                if (evBI < 55) rollback = 12;
            }
            if (buyIn >= 10 && buyIn < 25) {
                if (evBI >= 280) rollback = 95;
                if (evBI >= 195 && evBI <= 279) rollback = 93;
                if (evBI >= 145 && evBI <= 194) rollback = 91;
                if (evBI >= 105 && evBI <= 144) rollback = 88;
                if (evBI >= 80 && evBI <= 104) rollback = 85;
                if (evBI >= 65 && evBI <= 79) rollback = 82;
                if (evBI >= 50 && evBI <= 64) rollback = 79;
                if (evBI >= 40 && evBI <= 49) rollback = 76;
                if (evBI < 40) rollback = 8;
            }
            if (buyIn >= 25) {
                if (evBI >= 240) rollback = 95;
                if (evBI >= 190 && evBI <= 239) rollback = 94;
                if (evBI >= 140 && evBI <= 189) rollback = 92;
                if (evBI >= 120 && evBI <= 139) rollback = 91;
                if (evBI >= 105 && evBI <= 119) rollback = 90;
                if (evBI >= 85 && evBI <= 104) rollback = 88;
                if (evBI >= 70 && evBI <= 84) rollback = 86;
                if (evBI >= 50 && evBI <= 69) rollback = 85;
                if (evBI < 50) rollback = 6;
            }
        }

        if (mesh instanceof StudyWithoutBacking) {
            if (buyIn < 5) {
                if (evBI >= 225) rollback = 80;
                if (evBI >= 190 && evBI <= 224) rollback = 79;
                if (evBI >= 176 && evBI <= 189) rollback = 77;
                if (evBI >= 150 && evBI <= 175) rollback = 76;
                if (evBI >= 120 && evBI <= 149) rollback = 70;
                if (evBI >= 90 && evBI <= 119) rollback = 68;
                if (evBI >= 66 && evBI <= 89) rollback = 61;
                if (evBI <= 65) rollback = 25;
            }
            if (buyIn >= 5 && buyIn < 10) {
                if (evBI >= 225) rollback = 82;
                if (evBI >= 190 && evBI <= 224) rollback = 81;
                if (evBI >= 176 && evBI <= 189) rollback = 79;
                if (evBI >= 150 && evBI <= 175) rollback = 76;
                if (evBI >= 120 && evBI <= 149) rollback = 75;
                if (evBI >= 90 && evBI <= 119) rollback = 71;
                if (evBI >= 66 && evBI <= 89) rollback = 66;
                if (evBI >= 56 && evBI <= 65) rollback = 64;
                if (evBI <= 55) rollback = 20;
            }
            if (buyIn >= 10) {
                if (evBI >= 225) rollback = 85;
                if (evBI >= 190 && evBI <= 224) rollback = 84;
                if (evBI >= 176 && evBI <= 189) rollback = 82;
                if (evBI >= 150 && evBI <= 175) rollback = 81;
                if (evBI >= 120 && evBI <= 149) rollback = 75;
                if (evBI >= 90 && evBI <= 119) rollback = 73;
                if (evBI >= 66 && evBI <= 89) rollback = 66;
                if (evBI >= 56 && evBI <= 65) rollback = 62;
                if (evBI <= 55) rollback = 20;
            }
        }

        if (mesh instanceof ClearProfit) {
            rollback = 100;
        }

        model.setRollbackPct(rollback);

        return rollback;
    }

    private double getRequiredTourneys(int id) {
        HaveHoursModel model = getModel(id);
        double tourneys = model.getTables() * model.getTourneysPerTable() * model.getHaveHours();

        model.setRequiredTourneys(tourneys);

        return tourneys;
    }

    private double getEstimatedExpectation(int id) {
        HaveHoursModel model = getModel(id);
        double exp = (model.getExpEVT() + model.getBuyIn() * (model.getRake() / 100)
                                          * (model.getRakebackPct() / 100))
                     * model.getTables() * model.getTourneysPerTable() * model.getHaveHours();

        model.setEstimatedExpectation(exp);

        return exp;
    }

    private double getDollarPerHour(int id) {
        HaveHoursModel model = getModel(id);
        double dollarsPerHour = model.getEstimatedExpectation() / model.getHaveHours();

        model.setDollarsPerHour(dollarsPerHour);

        return dollarsPerHour;
    }

    private void calcFields(int id) {
        getRollback(id);
        getExpEVT(id);
        getRequiredTourneys(id);
        getEstimatedExpectation(id);
        getDollarPerHour(id);
    }

}
