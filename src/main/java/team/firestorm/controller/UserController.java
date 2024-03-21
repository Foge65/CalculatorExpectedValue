package team.firestorm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.firestorm.repository.Model;
import team.firestorm.service.Coefficient;
import team.firestorm.service.HyperEV;
import team.firestorm.service.room.*;

@RestController
@AllArgsConstructor
public class UserController {
    private final Model model;
    private final HyperEV hyperEV;
    private final Coefficient coefficient;
    private Room room;

    @GetMapping("/getRooms")
    public ResponseEntity<Rooms[]> allRooms() {
        return ResponseEntity.ok(Rooms.values());
    }

    @PostMapping("/setRoom")
    @ResponseBody
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
        model.setRoom(this.room);
    }

    @GetMapping("/getBuyIns")
    public ResponseEntity<double[]> getBuyIns() {
        return ResponseEntity.ok(this.room.buyIns());
    }

    @PostMapping("/setBuyIn")
    public void setBuyIn(@RequestParam("buyIn") double buyIn) {
        this.model.setBuyIn(buyIn);

        coefficient.setWinCoefficient(buyIn);
        coefficient.setLoseCoefficient(buyIn);
    }

    @GetMapping("/getRakes")
    public ResponseEntity<int[]> getRakes() {
        return ResponseEntity.ok(this.room.rakes());
    }

    @PostMapping("/setRake")
    public void setRake(@RequestParam("rake") int rake) {
        this.model.setRake(rake);
    }

    @PostMapping("/setTournaments")
    @ResponseBody
    public void tournaments(@RequestParam("tourney") int tourney) {
        this.model.setTournaments(tourney);
    }

    @PostMapping("/setChipsEV")
    @ResponseBody
    public void chipsEV(@RequestParam("chipsEV") double chipsEV) {
        this.model.setChipsEV(chipsEV);
    }

    @PostMapping("/setRakeBack")
    @ResponseBody
    public void rakeBack(@RequestParam("rakeBack") double rakeBack) {
        this.model.setRakeBack(rakeBack);
    }

    @PostMapping("/setTables")
    @ResponseBody
    public void tables(@RequestParam("tables") int tables) {
        this.model.setTables(tables);
    }

    @PostMapping("/setTablesPerHour")
    @ResponseBody
    public void tablesPerHour(@RequestParam("tables") double tables) {
        this.model.setTablesPerHour(tables);
    }

    @GetMapping("/hyperEV")
    public ResponseEntity<Double> hyperEV() {
        return ResponseEntity.ok(this.hyperEV.calculateCoefficients());
    }
}
