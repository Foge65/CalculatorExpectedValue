package team.firestorm.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.firestorm.dto.DesiredProfitRequestDTO;
import team.firestorm.model.DesiredProfitModel;
import team.firestorm.service.DesiredProfitService;
import team.firestorm.service.mesh.*;
import team.firestorm.service.room.*;

import java.util.Map;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/desiredProfit")
@RequiredArgsConstructor
public class DesiredProfitController {
    private final DesiredProfitService service;
    private final DesiredProfitModel model;
    private Room room;
    private Mesh mesh;

    @PostConstruct
    public void init() {
        model.setRooms(Rooms.values());
        initializeRoom();
        initializeMesh();
    }

    private void initializeRoom() {
        if (room == null) {
            room = new PokerStars();
            propertiesRoom();

            DesiredProfitRequestDTO requestDTO = new DesiredProfitRequestDTO();
            requestDTO.setBuyIn(0.25);
            setBuyIn(requestDTO);
        }
    }

    private void propertiesRoom() {
        model.setRoom(room);
        model.setBuyIns(room.buyIns());
        model.setRakes(room.rakes());
        model.setWinCoefficients(room.winCoefficient());
        model.setLoseCoefficients(room.loseCoefficient());
        model.setTourneysPerTable(room.tourneysPerTable());
    }

    private void initializeMesh() {
        if (mesh == null) {
            model.setMeshes(Meshes.values());
            mesh = new ClearProfit();
            model.setMesh(mesh);
        }
    }

    @GetMapping("/getData")
    public DesiredProfitModel getData() {
        return model;
    }

    @PostMapping("/setDesiredProfit")
    public void setDesiredProfit(@RequestBody DesiredProfitRequestDTO request) {
        model.setDesiredProfit(request.getDesiredProfit());
    }

    @PostMapping("/setRoom")
    public void setRoom(@RequestBody Map<String, String> request) {
        Rooms rooms = Rooms.valueOf(request.get("room"));
        createRoom(rooms);

        propertiesRoom();
    }

    private void createRoom(Rooms rooms) {
        switch (rooms) {
            case PokerStars -> new PokerStars();
            case Winamax -> new Winamax();
            case iPoker -> new IPoker();
        }
    }

    @PostMapping("/setBuyIn")
    public void setBuyIn(@RequestBody DesiredProfitRequestDTO request) {
        double buyIn = request.getBuyIn();
        model.setBuyIn(buyIn);

        int index = findIndexBySelectedCoefficient(room.buyIns(), buyIn);
        model.setRake(room.rakes()[index]);
        model.setWinCoefficient(room.winCoefficient()[index]);
        model.setLoseCoefficient(room.loseCoefficient()[index]);
    }

    private int findIndexBySelectedCoefficient(double[] buyIns, double buyIn) {
        return IntStream.range(0, buyIns.length).filter(i -> buyIns[i] == buyIn).findFirst().orElse(0);
    }

    @GetMapping("/getBuyIns")
    public double[] getBuyIns() {
        return model.getBuyIns();
    }

    @PostMapping("/setExpChipsT")
    public void setExpChipsT(@RequestBody DesiredProfitRequestDTO request) {
        model.setExpChipsT(request.getExpChipsT());
    }

    @GetMapping("/getExpEVT")
    public double getExpEVT() {
        return service.expDollarEVT();
    }

    @PostMapping("/setTables")
    public void setTables(@RequestBody DesiredProfitRequestDTO request) {
        model.setTables(request.getTables());
    }

    @PostMapping("/setRakebackPct")
    public void setRakebackPct(@RequestBody DesiredProfitRequestDTO request) {
        model.setRakebackPct(request.getRakebackPct());
    }

    @PostMapping("/setMesh")
    public void setMesh(@RequestBody Map<String, String> request) {
        Meshes meshes = Meshes.valueOf(request.get("mesh"));

        initializeMesh(meshes);
        model.setMesh(mesh);
    }

    private void initializeMesh(Meshes meshes) {
        switch (meshes) {
            case ClearProfit -> new ClearProfit();
            case BackingWithStudy -> new BackingWithStudy();
            case BackingWithoutStudy -> new BackingWithoutStudy();
        }
    }

    @GetMapping("/getRollback")
    public double getRollback() {
        return service.rollback();
    }

    @GetMapping("/getRequiredTourneys")
    public double getRequiredTourneys() {
        return service.requiredTourneys();
    }

    @GetMapping("/getRequiredHours")
    public double getRequiredHours() {
        return service.requiredHours();
    }

    @GetMapping("/getDollarPerHour")
    public double getDollarPerHour() {
        return service.dollarPerHour();
    }

}
