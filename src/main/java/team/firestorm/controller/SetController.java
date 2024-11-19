package team.firestorm.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.firestorm.repository.ModelRepository;
import team.firestorm.service.Coefficient;
import team.firestorm.service.Rake;
import team.firestorm.service.mesh.*;
import team.firestorm.service.room.*;

@RestController
@AllArgsConstructor
public class SetController {
    private final ModelRepository modelRepository;
    private final Coefficient coefficient;
    private final Rake rake;
    private Room room;
    private Mesh mesh;

    @PostMapping("/setRoom1")
    public void room1(@RequestParam("room") String room) {
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
        this.modelRepository.setRoom1(this.room);
        this.modelRepository.setBuyIns1(this.room.buyIns());
        this.modelRepository.setRakes1(this.room.rakes());
        this.modelRepository.setWinCoefficients1(this.room.winCoefficient());
        this.modelRepository.setLoseCoefficients1(this.room.loseCoefficient());
        this.modelRepository.setTourneysPerTable1(this.room.tourneysPerTable());
    }

    @PostMapping("/setRoom2")
    public void room2(@RequestParam("room") String room) {
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
        this.modelRepository.setRoom2(this.room);
        this.modelRepository.setBuyIns2(this.room.buyIns());
        this.modelRepository.setRakes2(this.room.rakes());
        this.modelRepository.setWinCoefficients2(this.room.winCoefficient());
        this.modelRepository.setLoseCoefficients2(this.room.loseCoefficient());
        this.modelRepository.setTourneysPerTable2(this.room.tourneysPerTable());
    }

    @PostMapping("/setBuyIn1")
    public void buyIn1(@RequestParam("buyIn") double buyIn) {
        this.modelRepository.setBuyIn1(buyIn);

        this.coefficient.setWinCoefficient1(buyIn);
        this.coefficient.setLoseCoefficient1(buyIn);

        this.rake.setRake1(buyIn);
    }

    @PostMapping("/setBuyIn2")
    public void buyIn2(@RequestParam("buyIn") double buyIn) {
        this.modelRepository.setBuyIn2(buyIn);

        this.coefficient.setWinCoefficient2(buyIn);
        this.coefficient.setLoseCoefficient2(buyIn);

        this.rake.setRake2(buyIn);
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

    @PostMapping("/setMesh1")
    public void mesh1(@RequestParam("mesh") String mesh) {
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
        this.modelRepository.setMesh1(this.mesh);
    }

    @PostMapping("/setMesh2")
    public void mesh2(@RequestParam("mesh") String mesh) {
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
        this.modelRepository.setMesh2(this.mesh);
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
        modelRepository.setDesiredProfit(0);
        modelRepository.setExpChipsT1(0);
        modelRepository.setExpChipsT2(0);
        modelRepository.setTables1(0);
        modelRepository.setTables2(0);
        modelRepository.setRakebackPercent1(0);
        modelRepository.setRakebackPercent2(0);
        modelRepository.setHaveHours(0);

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
