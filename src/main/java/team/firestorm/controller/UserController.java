package team.firestorm.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.firestorm.HyperEV;
import team.firestorm.UserData;
import team.firestorm.room.*;

@RestController
@AllArgsConstructor
public class UserController {
    private Room room;
    private UserData userData;
    private HyperEV hyperEV;

    @GetMapping("/getRooms")
    public Rooms[] allRooms() {
        return Rooms.values();
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
        userData.setRoom(this.room);
    }

    @GetMapping("/getBuyIns")
    public double[] getBuyIns() {
        return this.room.buyIns();
    }

    @PostMapping("/setBuyIn")
    public void setBuyIn(@RequestParam("buyIn") double buyIn) {
        this.userData.setBuyIn(buyIn);
    }

    @GetMapping("/getRakes")
    public int[] getRakes() {
        return this.room.rakes();
    }

    @PostMapping("/setRake")
    public void setRake(@RequestParam("rake") int rake) {
        this.userData.setRake(rake);
    }

    @PostMapping("/setTournaments")
    @ResponseBody
    public void tournaments(@RequestParam("tourney") int tourney) {
        this.userData.setTournaments(tourney);
    }

    @PostMapping("/setChipsEV")
    @ResponseBody
    public void chipsEV(@RequestParam("chipsEV") double chipsEV) {
        this.userData.setChipsEV(chipsEV);
    }

    @PostMapping("/setRakeBack")
    @ResponseBody
    public void rakeBack(@RequestParam("rakeBack") double rakeBack) {
        this.userData.setRakeBack(rakeBack);
    }

    @PostMapping("/setTables")
    @ResponseBody
    public void tables(@RequestParam("tables") int tables) {
        this.userData.setTables(tables);
    }

    @PostMapping("/setTablesPerHour")
    @ResponseBody
    public void tablesPerHour(@RequestParam("tables") double tables) {
        this.userData.setTablesPerHour(tables);
    }

    @GetMapping("/hyperEV")
    public double hyperEV() {
        return this.hyperEV.calculateCoefficients();
    }
}
