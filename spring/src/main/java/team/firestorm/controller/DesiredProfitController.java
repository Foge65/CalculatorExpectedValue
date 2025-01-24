package team.firestorm.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.firestorm.dto.DesiredProfitRequestDTO;
import team.firestorm.model.DesiredProfitModel;
import team.firestorm.service.DesiredProfitModelService;
import team.firestorm.service.mesh.*;
import team.firestorm.service.room.*;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/desiredProfit")
@RequiredArgsConstructor
public class DesiredProfitController {
    private final DesiredProfitModelService modelService;
    private Room room;
    private Mesh mesh;

    @PostConstruct
    public void init() {
        createModel();
        DesiredProfitModel model = modelService.findLastModel();
        model.setRooms(Rooms.values());
        initializeRoom(model);
        initializeMesh(model);
    }

    @PostMapping("/createModel")
    public void createModel() {
        modelService.addModel();
        DesiredProfitModel model = modelService.findLastModel();
        model.setRooms(Rooms.values());
        initializeRoom(model);
        initializeMesh(model);
        initializeCalcFields(model);
    }

    private void initializeRoom(DesiredProfitModel model) {
        room = new PokerStars();
        propertiesRoom(model);

        DesiredProfitRequestDTO requestDTO = new DesiredProfitRequestDTO();
        requestDTO.setBuyIn(0.25);
        int id = modelService.findLastId();
        setBuyIn(id, requestDTO);
    }

    private void propertiesRoom(DesiredProfitModel model) {
        model.setRoom(room);
        model.setBuyIns(room.buyIns());
        model.setRakes(room.rakes());
        model.setWinCoefficients(room.winCoefficient());
        model.setLoseCoefficients(room.loseCoefficient());
        model.setTourneysPerTable(room.tourneysPerTable());
    }

    private void initializeMesh(DesiredProfitModel model) {
        if (mesh == null) {
            model.setMeshes(Meshes.values());
            mesh = new ClearProfit();
            model.setMesh(mesh);
        }
    }

    private void initializeCalcFields(DesiredProfitModel model) {
        int id = modelService.findLastId();
        model.setExpEVT(getExpEVT(id));
        model.setRollbackPct(getRollback(id));
        model.setRequiredTourneys(getRequiredTourneys(id));
        model.setRequiredHours(getRequiredHours(id));
        model.setDollarsPerHour(getDollarPerHour(id));
    }

    @PostMapping("/removeModel")
    public void removeModel(@RequestParam("id") int id) {
        modelService.removeModelById(id);
    }

    @GetMapping("/getModel")
    public DesiredProfitModel getModel(@RequestParam("id") int id) {
        DesiredProfitModel model = modelService.getModels().get(id);
        if (model == null) {
            throw new NoSuchElementException("Model with id " + id + " not found");
        }
        return model;
    }

    @GetMapping("/getAllData")
    public Map<Integer, DesiredProfitModel> getData() {
        return modelService.getModels();
    }

    @PostMapping("/setDesiredProfit")
    public DesiredProfitModel setDesiredProfit(@RequestParam("id") int id, @RequestBody DesiredProfitRequestDTO request) {
        DesiredProfitModel model = getModel(id);
        model.setDesiredProfit(request.getDesiredProfit());
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
    public DesiredProfitModel setRoom(@RequestParam("id") int id, @RequestBody Map<String, String> request) {
        DesiredProfitModel model = getModel(id);
        Rooms rooms = Rooms.valueOf(request.get("room"));
        createRoom(rooms);
        propertiesRoom(model);
        return model;
    }

    private void createRoom(Rooms rooms) {
        switch (rooms) {
            case PokerStars -> room = new PokerStars();
            case Winamax -> room = new Winamax();
            case iPoker -> room = new IPoker();
        }
    }

    @PostMapping("/setBuyIn")
    public DesiredProfitModel setBuyIn(@RequestParam("id") int id, @RequestBody DesiredProfitRequestDTO request) {
        DesiredProfitModel model = getModel(id);
        double buyIn = request.getBuyIn();
        model.setBuyIn(buyIn);

        int index = findIndexBySelectedCoefficient(room.buyIns(), buyIn);
        model.setRake(room.rakes()[index]);
        model.setWinCoefficient(room.winCoefficient()[index]);
        model.setLoseCoefficient(room.loseCoefficient()[index]);

        return model;
    }

    private int findIndexBySelectedCoefficient(double[] buyIns, double buyIn) {
        return IntStream.range(0, buyIns.length).filter(i -> buyIns[i] == buyIn).findFirst().orElse(0);
    }

    @GetMapping("/getBuyIns")
    public double[] getBuyIns(@RequestParam("id") int id) {
        DesiredProfitModel model = getModel(id);
        return model.getBuyIns();
    }

    @GetMapping("/getBuyIn")
    public double getBuyIn(@RequestParam("id") int id) {
        return getModel(id).getBuyIn();
    }

    @PostMapping("/setExpChipsT")
    public DesiredProfitModel setExpChipsT(@RequestParam("id") int id, @RequestBody DesiredProfitRequestDTO request) {
        DesiredProfitModel model = getModel(id);
        model.setExpChipsT(request.getExpChipsT());
        return model;
    }

    @GetMapping("/getExpEVT")
    public double getExpEVT(@RequestParam("id") int id) {
        DesiredProfitModel model = getModel(id);
        double buyIn = model.getBuyIn();
        double chipsEV = model.getExpChipsT();
        double winCoefficient = model.getWinCoefficient();
        double loseCoefficient = model.getLoseCoefficient();

        double expEVT = buyIn * 1 * (((500 + chipsEV) / 1500) * winCoefficient
                                     + (1 - ((500 + chipsEV) / 1500)) * loseCoefficient);

        model.setExpEVT(expEVT);

        return expEVT;
    }

    @PostMapping("/setTables")
    public DesiredProfitModel setTables(@RequestParam("id") int id, @RequestBody DesiredProfitRequestDTO request) {
        DesiredProfitModel model = getModel(id);
        model.setTables(request.getTables());
        return model;
    }

    @PostMapping("/setRakebackPct")
    public DesiredProfitModel setRakebackPct(@RequestParam("id") int id, @RequestBody DesiredProfitRequestDTO request) {
        DesiredProfitModel model = getModel(id);
        model.setRakebackPct(request.getRakebackPct());
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
    public DesiredProfitModel setMesh(@RequestParam("id") int id, @RequestBody Map<String, String> request) {
        DesiredProfitModel model = getModel(id);
        Meshes meshes = Meshes.valueOf(request.get("mesh"));

        initializeMesh(meshes);
        model.setMesh(mesh);

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

    @GetMapping("/getRollback")
    public double getRollback(@RequestParam("id") int id) {
        DesiredProfitModel model = getModel(id);
        double buyIn = model.getBuyIn();
        double evBI = model.getDesiredProfit() / buyIn;
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

    @GetMapping("/getRequiredTourneys")
    public double getRequiredTourneys(@RequestParam("id") int id) {
        DesiredProfitModel model = getModel(id);
        double desiredProfit = model.getDesiredProfit();
        double buyIn = model.getBuyIn();
        double rollback = model.getRollbackPct();
        double rake = model.getRake();
        double rakeback = model.getRakebackPct();
        double dollarEVPerTourney = model.getExpEVT();
        double requiredTourneys = desiredProfit / (rollback / 100) / (buyIn * (rake / 100) * (rakeback / 100)
                                                                      + dollarEVPerTourney) + 1;
        model.setRequiredTourneys(requiredTourneys);
        return requiredTourneys;
    }

    @GetMapping("/getRequiredHours")
    public double getRequiredHours(@RequestParam("id") int id) {
        DesiredProfitModel model = getModel(id);
        double requiredHours = model.getRequiredTourneys() / (model.getTables() * model.getTourneysPerTable());
        model.setRequiredHours(requiredHours);
        return requiredHours;
    }

    @GetMapping("/getDollarPerHour")
    public double getDollarPerHour(@RequestParam("id") int id) {
        DesiredProfitModel model = getModel(id);
        double dollarsPerHour = model.getDesiredProfit() / model.getRequiredHours();
        model.setDollarsPerHour(dollarsPerHour);
        return dollarsPerHour;
    }

}
