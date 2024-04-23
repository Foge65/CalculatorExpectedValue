package team.firestorm.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.firestorm.repository.ModelRepository;
import team.firestorm.service.Coefficient;
import team.firestorm.service.Rake;
import team.firestorm.service.mesh.*;
import team.firestorm.service.room.*;

@RestController
@RequestMapping("/calcEV")
@AllArgsConstructor
public class SetController {
    private final ModelRepository modelRepository;
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
        this.modelRepository.setRoom(this.room);
        this.modelRepository.setBuyIns(this.room.buyIns());
        this.modelRepository.setRakes(this.room.rakes());
        this.modelRepository.setWinCoefficients(this.room.winCoefficient());
        this.modelRepository.setLoseCoefficients(this.room.loseCoefficient());
    }

    @PostMapping("/setBuyIn")
    public void buyIn(@RequestParam("buyIn") double buyIn) {
        this.modelRepository.setBuyIn(buyIn);

        this.coefficient.setWinCoefficient(buyIn);
        this.coefficient.setLoseCoefficient(buyIn);

        this.rake.setRake(buyIn);
    }

    @PostMapping("/setTourneyPerPeriod")
    public void tourneyPerPeriod(@RequestParam("tourney") int tourney) {
        this.modelRepository.setTourneyPerPeriod(tourney);
    }

    @PostMapping("/setChipsEVFromTourney")
    public void chipsEV(@RequestParam("chips") double chips) {
        this.modelRepository.setChipsEVFromTourney(chips);
    }

    @PostMapping("/setRakeBackTotal")
    public void rakeBack(@RequestParam("rakeBack") double rakeBack) {
        this.modelRepository.setRakeBackTotal(rakeBack);
    }

    @PostMapping("/setDollarsPerHour")
    public void dollarPerHour(@RequestParam("dollars") double dollars) {
        this.modelRepository.setDollarsPerHour(dollars);
    }

    @PostMapping("/setHoursPerDay")
    public void hourPerDay(@RequestParam("hours") double hours) {
        this.modelRepository.setHoursPerDay(hours);
    }

    @PostMapping("/setDaysPerWeek")
    public void dayPerWeek(@RequestParam("days") int days) {
        this.modelRepository.setDaysPerWeek(days);
    }

    @PostMapping("/setDaysPerMonth")
    public void dayPerMonth(@RequestParam("days") int days) {
        this.modelRepository.setDaysPerMonth(days);
    }

    @PostMapping("/setRakeBackDollarsPerDay")
    public void rakeBackDollarPerDay(@RequestParam("payments") double payments) {
        this.modelRepository.setRakeBackDollarsPerDay(payments);
    }

    @PostMapping("/setRakeBackDollarsPerWeek")
    public void rakeBackDollarPerWeek(@RequestParam("payments") double payments) {
        this.modelRepository.setRakeBackDollarsPerWeek(payments);
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
        this.modelRepository.setMesh(this.mesh);
    }

    @PostMapping("/setTourneyPerDay")
    public void tourneyPerDay(@RequestParam("tourney") int tourney) {
        this.modelRepository.setTourneyPerDay(tourney);
    }

    @PostMapping("/setWeeksPerPeriod")
    public void weekPerPeriod(@RequestParam("weeks") int weeks) {
        this.modelRepository.setWeeksPerPeriod(weeks);
    }

    @PostMapping("/setRakeBackDollarsPerPeriod")
    public void rakeBackDollarPerPeriod(@RequestParam("dollars") double dollars) {
        this.modelRepository.setRakeBackDollarsPerPeriod(dollars);
    }

    @PostMapping("/resetAllFields")
    public void resetAllField() {
        this.modelRepository.setDollarsPerHour(0);
        this.modelRepository.setHoursPerDay(0);
        this.modelRepository.setDaysPerMonth(0);
        this.modelRepository.setChipsEVFromTourney(0);
        this.modelRepository.setDollarsEVPerTourney(0);
        this.modelRepository.setRakeBackTotal(0);
        this.modelRepository.setTourneyPerPeriod(0);
        this.modelRepository.setTourneyPerDay(0);
        this.modelRepository.setRakeBackDollarsPerDay(0);
        this.modelRepository.setDaysPerWeek(0);
        this.modelRepository.setRakeBackDollarsPerWeek(0);
        this.modelRepository.setWeeksPerPeriod(0);
        this.modelRepository.setRakeBackDollarsPerPeriod(0);
    }
}
