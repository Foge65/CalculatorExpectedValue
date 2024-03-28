package team.firestorm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.firestorm.repository.Model;
import team.firestorm.service.Coefficient;
import team.firestorm.service.EV;
import team.firestorm.service.Rake;
import team.firestorm.service.mesh.*;
import team.firestorm.service.room.*;

@RestController
@AllArgsConstructor
@RequestMapping("/calcEV")
public class UserController {
    private final Model model;
    private final EV ev;
    private final Coefficient coefficient;
    private final Rake rake;
    private Room room;
    private Mesh mesh;

    @GetMapping("/getRooms")
    public ResponseEntity<Rooms[]> allRooms() {
        return ResponseEntity.ok(Rooms.values());
    }

    @PostMapping("/setRoom")
    public void setRoom(@RequestParam("room") String room) {
        Rooms rooms = Rooms.valueOf(room);
        switch (rooms) {
            case PokerStars:
                this.room = new PokerStars();
                break;
            case Winamax:
                this.room = new Winamax();
                break;
            case iPoker:
                this.room = new IPoker();
                break;
        }
        this.model.setRoom(this.room);
    }

    @GetMapping("/getBuyIns")
    public ResponseEntity<double[]> getBuyIns() {
        return ResponseEntity.ok(this.room.buyIns());
    }

    @PostMapping("/setBuyIn")
    public void setBuyIn(@RequestParam("buyIn") double buyIn) {
        this.model.setBuyIn(buyIn);

        this.coefficient.setWinCoefficient(buyIn);
        this.coefficient.setLoseCoefficient(buyIn);

        this.rake.setRake(buyIn);
    }

    @PostMapping("/setTournaments")
    public void tournaments(@RequestParam("tourney") int tourney) {
        this.model.setTournaments(tourney);
    }

    @PostMapping("/setChipsEV")
    public void chipsEV(@RequestParam("chipsEV") double chipsEV) {
        this.model.setChipsEV(chipsEV);
    }

    @GetMapping("/hyperEV")
    public ResponseEntity<Double> hyperEV() {
        return ResponseEntity.ok(this.ev.hyperEV());
    }

    @PostMapping("/setRakeBack")
    public void rakeBack(@RequestParam("rakeBack") double rakeBack) {
        this.model.setRakeBack(rakeBack);
    }

    @PostMapping("/setTables")
    public void tables(@RequestParam("tables") int tables) {
        this.model.setTables(tables);
    }

    @PostMapping("/setTablesPerHour")
    public void tablesPerHour(@RequestParam("tables") double tables) {
        this.model.setTablesPerHour(tables);
    }

    @PostMapping("/setDollarsPerHour")
    public void setDollarsPerHour(@RequestParam("dollars") double dollars) {
        this.model.setDollarsPerHour(dollars);
    }

    @PostMapping("/setHoursPerDay")
    public void setHoursPerDay(@RequestParam("hours") double hours) {
        this.model.setHoursPerDay(hours);
    }

    @PostMapping("/setDaysPerMonth")
    public void setDaysPerMonth(@RequestParam("days") int days) {
        this.model.setDaysPerMonth(days);
    }

    @GetMapping("/evTotal")
    public ResponseEntity<Double> evTotal() {
        return ResponseEntity.ok(this.ev.evTotal());
    }

    @PostMapping("/setDollarsEVPerTourney")
    public void setDollarsEVPerTourney(@RequestParam("dollars") double dollars) {
        this.model.setDollarsEVPerTourney(dollars);
    }

    @GetMapping("/profitTotal")
    public ResponseEntity<Double> profitTotal() {
        return ResponseEntity.ok(this.ev.profitTotal());
    }

    @PostMapping("/setOtherBonuses")
    public void setOtherBonuses(@RequestParam("dollars") double dollars) {
        this.model.setOtherPayments(dollars);
    }

    @GetMapping("/getMeshes")
    public ResponseEntity<Meshes[]> getMeshes() {
        return ResponseEntity.ok(Meshes.values());
    }

    @PostMapping("/setMesh")
    public void setMesh(@RequestParam("mesh") String mesh) {
        Meshes meshes = Meshes.valueOf(mesh);
        switch (meshes) {
            case Study:
                this.mesh = new Study();
                break;
            case BackingWithoutStudy:
                this.mesh = new BackingWithoutStudy();
                break;
            case StudyAndBacking:
                this.mesh = new StudyAndBacking();
                break;
        }
        this.model.setMesh(this.mesh);
    }

    @GetMapping("/evBI")
    public ResponseEntity<Double> evBI() {
        return ResponseEntity.ok(this.ev.evBI());
    }

    @GetMapping("/evTotTourney")
    public ResponseEntity<Double> evTotTourney() {
        return ResponseEntity.ok(this.ev.profitTotalPerTourney());
    }
}
