package team.firestorm.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.firestorm.dto.HaveHoursPerMonthRequestDTO;
import team.firestorm.model.HaveHoursPerMonthModel;
import team.firestorm.service.mesh.*;
import team.firestorm.service.room.*;

import java.util.Map;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/haveHoursPerMonth")
@RequiredArgsConstructor
public class HaveHoursPerMonthController {
    private final HaveHoursPerMonthModel model;
    private Room room;
    private Mesh mesh;

    @PostConstruct
    @GetMapping("/getData")
    public HaveHoursPerMonthModel getData() {
        model.setRooms(Rooms.values());

        room = new PokerStars();
        model.setRoom(room);
        model.setBuyIns(room.buyIns());
        model.setRakes(room.rakes());
        model.setWinCoefficients(room.winCoefficient());
        model.setLoseCoefficients(room.loseCoefficient());
        model.setTourneysPerTable(room.tourneysPerTable());

        if (mesh == null) {
            model.setMeshes(Meshes.values());
            mesh = new ClearProfit();
            model.setMesh(mesh);
        }

        return model;
    }

    @PostMapping("/setHaveHours")
    public void setHaveHours(@RequestBody HaveHoursPerMonthRequestDTO request) {
        model.setHaveHours(request.getHaveHours());
    }

    @PostMapping("/setTables")
    public void setTables(@RequestBody HaveHoursPerMonthRequestDTO request) {
        model.setTables(request.getTables());
    }

    @PostMapping("/setRoom")
    public void setRoom(@RequestBody Map<String, String> request) {
        Rooms rooms = Rooms.valueOf(request.get("room"));

        switch (rooms) {
            case PokerStars:
                room = new PokerStars();
                break;
            case Winamax:
                room = new Winamax();
                break;
            case iPoker:
                room = new IPoker();
                break;
        }

        model.setRoom(room);
        model.setBuyIns(room.buyIns());
        model.setRakes(room.rakes());
        model.setWinCoefficients(room.winCoefficient());
        model.setLoseCoefficients(room.loseCoefficient());
        model.setTourneysPerTable(room.tourneysPerTable());
    }

    @PostMapping("/setBuyIn")
    public void setBuyIn(@RequestBody HaveHoursPerMonthRequestDTO request) {
        double buyIn = request.getBuyIn();
        model.setBuyIn(buyIn);

        int index = findIndexBySelectedCoefficient(room.buyIns(), buyIn);
        model.setRake(room.rakes()[index]);
        model.setWinCoefficient(room.winCoefficient()[index]);
        model.setLoseCoefficient(room.loseCoefficient()[index]);
    }

    @PostMapping("/setExpChipsT")
    public void setExpChipsT(@RequestBody HaveHoursPerMonthRequestDTO request) {
        model.setExpChipsT(request.getExpChipsT());
    }

    @GetMapping("/getExpEVT")
    public double getExpEVT() {
        double buyIn = model.getBuyIn();
        double chipsEV = model.getExpChipsT();
        double winCoefficient = model.getWinCoefficient();
        double loseCoefficient = model.getLoseCoefficient();

        double expEVT = buyIn * 1 * (((500 + chipsEV) / 1500) * winCoefficient
                                     + (1 - ((500 + chipsEV) / 1500)) * loseCoefficient);

        model.setExpDollarEVT(expEVT);

        return expEVT;
    }

    @PostMapping("/setRakebackPct")
    public void setRakebackPct(@RequestBody HaveHoursPerMonthRequestDTO request) {
        model.setRakebackPct(request.getRakebackPct());
    }

    @PostMapping("/setMesh")
    public void setMesh(@RequestBody Map<String, String> request) {
        Meshes meshes = Meshes.valueOf(request.get("mesh"));

        switch (meshes) {
            case BackingWithStudy:
                mesh = new BackingWithStudy();
                break;
            case BackingWithoutStudy:
                mesh = new BackingWithoutStudy();
                break;
            case StudyWithoutBacking:
                mesh = new StudyWithoutBacking();
                break;
        }
        model.setMesh(mesh);
    }

    @GetMapping("/getRollback")
    public double getRollback() {
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

        model.setRollback(rollback);

        return rollback;
    }

    @GetMapping("/getRequiredTourneys")
    public double getRequiredTourneys() {
        double tourneys = model.getTables() * model.getTourneysPerTable() * model.getHaveHours();

        model.setRequiredTourneys(tourneys);

        return tourneys;
    }

    @GetMapping("/getEstimatedExpectation")
    public double getEstimatedExpectation() {
        double exp = (model.getExpDollarEVT() + model.getBuyIn() * (model.getRake() / 100)
                                                * (model.getRakebackPct() / 100))
                     * model.getTables() * model.getTourneysPerTable() * model.getHaveHours();

        model.setEstimatedExpectation(exp);

        return exp;
    }

    @GetMapping("/getDollarPerHour")
    public double getDollarPerHour() {
        double dollarsPerHour = model.getEstimatedExpectation() / model.getHaveHours();

        model.setDollarPerHour(dollarsPerHour);

        return dollarsPerHour;
    }

    private int findIndexBySelectedCoefficient(double[] buyIns, double buyIn) {
        return IntStream.range(0, buyIns.length).filter(i -> buyIns[i] == buyIn).findFirst().orElse(0);
    }

}
