package team.firestorm.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.firestorm.repository.Model;
import team.firestorm.service.Coefficient;
import team.firestorm.service.Rake;
import team.firestorm.service.mesh.*;
import team.firestorm.service.room.*;

@RestController
@RequestMapping("/calcEV")
@AllArgsConstructor
public class SetController {
    private final Model model;
    private final Coefficient coefficient;
    private final Rake rake;
    private Room room;
    private Mesh mesh;

    @PostMapping("/setRoom")
    public void room(@RequestParam("room") String room) {
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
        this.model.setBuyIns(this.room.buyIns());
        this.model.setRakes(this.room.rakes());
        this.model.setWinCoefficients(this.room.winCoefficient());
        this.model.setLoseCoefficients(this.room.loseCoefficient());
    }

    @PostMapping("/setBuyIn")
    public void buyIn(@RequestParam("buyIn") double buyIn) {
        this.model.setBuyIn(buyIn);

        this.coefficient.setWinCoefficient(buyIn);
        this.coefficient.setLoseCoefficient(buyIn);

        this.rake.setRake(buyIn);
    }

    @PostMapping("/setTourneyPerPeriod")
    public void tourneyPerPeriod(@RequestParam("tourney") int tourney) {
        this.model.setTourneyPerPeriod(tourney);
    }

    @PostMapping("/setChipsEVFromTourney")
    public void chipsEV(@RequestParam("chips") double chips) {
        this.model.setChipsEVFromTourney(chips);
    }

    @PostMapping("/setRakeBackTotal")
    public void rakeBack(@RequestParam("rakeBack") double rakeBack) {
        this.model.setRakeBackTotal(rakeBack);
    }

    @PostMapping("/setDollarsPerHour")
    public void dollarPerHour(@RequestParam("dollars") double dollars) {
        this.model.setDollarsPerHour(dollars);
    }

    @PostMapping("/setHoursPerDay")
    public void hourPerDay(@RequestParam("hours") double hours) {
        this.model.setHoursPerDay(hours);
    }

    @PostMapping("/setDaysPerWeek")
    public void dayPerWeek(@RequestParam("days") int days) {
        this.model.setDaysPerWeek(days);
    }

    @PostMapping("/setDaysPerMonth")
    public void dayPerMonth(@RequestParam("days") int days) {
        this.model.setDaysPerMonth(days);
    }

    @PostMapping("/setRakeBackDollarsPerDay")
    public void rakeBackDollarPerDay(@RequestParam("payments") double payments) {
        this.model.setRakeBackDollarsPerDay(payments);
    }

    @PostMapping("/setRakeBackDollarsPerWeek")
    public void rakeBackDollarPerWeek(@RequestParam("payments") double payments) {
        this.model.setRakeBackDollarsPerWeek(payments);
    }

    @PostMapping("/setMesh")
    public void mesh(@RequestParam("mesh") String mesh) {
        Meshes meshes = Meshes.valueOf(mesh);
        switch (meshes) {
            case BackingWithStudy:
                this.mesh = new BackingWithStudy();
                break;
            case BackingWithoutStudy:
                this.mesh = new BackingWithoutStudy();
                break;
            case StudyWithoutBacking:
                this.mesh = new StudyWithoutBacking();
                break;
        }
        this.model.setMesh(this.mesh);
    }

    @PostMapping("/setTourneyPerDay")
    public void tourneyPerDay(@RequestParam("tourney") int tourney) {
        this.model.setTourneyPerDay(tourney);
    }

    @PostMapping("/setWeeksPerPeriod")
    public void weekPerPeriod(@RequestParam("weeks") int weeks) {
        this.model.setWeeksPerPeriod(weeks);
    }

    @PostMapping("/setRakeBackDollarsPerPeriod")
    public void rakeBackDollarPerPeriod(@RequestParam("dollars") double dollars) {
        this.model.setRakeBackDollarsPerPeriod(dollars);
    }

    @PostMapping("/resetAllFields")
    public void resetAllField() {
        this.model.setDollarsPerHour(0);
        this.model.setHoursPerDay(0);
        this.model.setDaysPerMonth(0);
        this.model.setChipsEVFromTourney(0);
        this.model.setDollarsEVPerTourney(0);
        this.model.setRakeBackTotal(0);
        this.model.setTourneyPerPeriod(0);
        this.model.setTourneyPerDay(0);
        this.model.setRakeBackDollarsPerDay(0);
        this.model.setDaysPerWeek(0);
        this.model.setRakeBackDollarsPerWeek(0);
        this.model.setWeeksPerPeriod(0);
        this.model.setRakeBackDollarsPerPeriod(0);
    }
}
