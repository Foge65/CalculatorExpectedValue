package team.firestorm.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.firestorm.entity.Room;
import team.firestorm.entity.Rooms;
import team.firestorm.service.HyperEV;
import team.firestorm.service.UserData;

@RestController
@AllArgsConstructor
public class UserController {
    private Room room;
    private UserData userData;
    private HyperEV hyperEV;

    @GetMapping("/room")
    public Rooms[] getRoom() {
        return Rooms.values();
    }

    @GetMapping("/buyIn")
    public double[] getBuyIns() {
        return room.buyIns();
    }

    @GetMapping("/rake")
    public int[] getRakes() {
        return room.rakes();
    }

    @PostMapping("/tables")
    @ResponseBody
    public void setNumberTables(@RequestParam("number") int number) {
        userData.setNumberTables(number);
    }

    @PostMapping("/tablesPerHour")
    @ResponseBody
    public void setNumberTablesPerHour(@RequestParam("double") double number) {
        userData.setNumberTablesPerHour(number);
    }

    @PostMapping("/chipsEV")
    @ResponseBody
    public void setChipsEV(@RequestParam("double") double number) {
        userData.setChipsEV(number);
    }

    @PostMapping("/rakeBack")
    @ResponseBody
    public void setRackBack(@RequestParam("double") double number) {
        userData.setRakeBack(number);
    }

    @PostMapping("/tournaments")
    @ResponseBody
    public void setTournaments(@RequestParam("number") int number) {
        userData.setTournaments(number);
    }

    @GetMapping("/hyperEV")
    public double getHyperEV() {
        return hyperEV.calculateCoefficients();
    }
}
