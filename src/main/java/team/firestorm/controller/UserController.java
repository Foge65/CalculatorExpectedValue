package team.firestorm.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.firestorm.HyperEV;
import team.firestorm.UserData;
import team.firestorm.room.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {
    private Room room;
    private UserData userData;
    private HyperEV hyperEV;

    @GetMapping("/allRooms")
    public Rooms[] allRooms() {
        return Rooms.values();
    }

    @GetMapping("/getRoom")
    public Room getRoom() {
        return userData.getRoom();
    }

    @PostMapping("/setRoom")
    @ResponseBody
    public void setRoom(@RequestParam("roomClass") String roomClass) {
        Rooms rooms = Rooms.valueOf(roomClass);
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
        userData.setRoom(room);
    }

    @GetMapping("/buyIns")
    public double[] buyIns() {
        return room.buyIns();
    }

    @PostMapping("/buyIn")
    public void buyIn(@RequestParam("buyIn") double buyIn) {
        userData.setBuyIn(buyIn);
    }

    @GetMapping("/rakes")
    public int[] rakes() {
        return room.rakes();
    }

    @PostMapping("/tournaments")
    @ResponseBody
    public void tournaments(@RequestParam("number") int number) {
        userData.setTournaments(number);
    }

    @PostMapping("/chipsEV")
    @ResponseBody
    public void chipsEV(@RequestParam("double") double number) {
        userData.setChipsEV(number);
    }

    @PostMapping("/rakeBack")
    @ResponseBody
    public void rakeBack(@RequestParam("double") double number) {
        userData.setRakeBack(number);
    }

    @PostMapping("/tables")
    @ResponseBody
    public void tables(@RequestParam("number") int number) {
        userData.setNumberTables(number);
    }

    @PostMapping("/tablesPerHour")
    @ResponseBody
    public void tablesPerHour(@RequestParam("double") double number) {
        userData.setNumberTablesPerHour(number);
    }

    @GetMapping("/hyperEV")
    public double hyperEV() {
        return hyperEV.calculateCoefficients();
    }
}
