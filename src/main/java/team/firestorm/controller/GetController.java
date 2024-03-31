package team.firestorm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.firestorm.repository.Model;
import team.firestorm.service.DontKnowMyTotalRakeBack;
import team.firestorm.service.HyperEV;
import team.firestorm.service.KnowMyDollarPerHour;
import team.firestorm.service.KnowMyTotalRakeBack;
import team.firestorm.service.mesh.Meshes;
import team.firestorm.service.room.Room;
import team.firestorm.service.room.Rooms;

@RestController
@RequestMapping("/calcEV")
@AllArgsConstructor
public class GetController {
    private final HyperEV hyperEV;
    private final KnowMyDollarPerHour knowMyDollarPerHour;
    private final KnowMyTotalRakeBack knowMyTotalRakeBack;
    private final DontKnowMyTotalRakeBack dontKnowMyTotalRakeBack;
    private Room room;
    private Model model;

    @GetMapping("/getRooms")
    public ResponseEntity<Rooms[]> rooms() {
        return ResponseEntity.ok(Rooms.values());
    }

    @GetMapping("/getBuyIns")
    public ResponseEntity<double[]> buyIns() {
        return ResponseEntity.ok(this.room.buyIns());
    }

    @GetMapping("/getDollarsEVPerTourney")
    public ResponseEntity<Double> dollarEVPerTourney() {
        return ResponseEntity.ok(this.hyperEV.hyperEV() / this.model.getTourneyPerPeriod());
    }

    @GetMapping("/dollarEVTotal1")
    public ResponseEntity<Double> dollarEVTotal1() {
        return ResponseEntity.ok(this.knowMyDollarPerHour.dollarEVTotal());
    }

    @GetMapping("/dollarEVTotal2")
    public ResponseEntity<Double> dollarEVTotal2() {
        return ResponseEntity.ok(this.knowMyTotalRakeBack.dollarEVTotal());
    }

    @GetMapping("/dollarEVTotal3")
    public ResponseEntity<Double> dollarEVTotal3() {
        return ResponseEntity.ok(this.dontKnowMyTotalRakeBack.dollarEVTotal());
    }

    @GetMapping("/getMeshes")
    public ResponseEntity<Meshes[]> meshes() {
        return ResponseEntity.ok(Meshes.values());
    }

    @GetMapping("/evBI1")
    public ResponseEntity<Double> evBI1() {
        return ResponseEntity.ok(this.knowMyDollarPerHour.evBI());
    }

    @GetMapping("/evBI2")
    public ResponseEntity<Double> evBI2() {
        return ResponseEntity.ok(this.knowMyTotalRakeBack.evBI());
    }

    @GetMapping("/evBI3")
    public ResponseEntity<Double> evBI3() {
        return ResponseEntity.ok(this.dontKnowMyTotalRakeBack.evBI());
    }

    @GetMapping("/getDollarEVTotalPerTourney2")
    public ResponseEntity<Double> dollarEVTotalPerTourney2() {
        return ResponseEntity.ok(this.knowMyTotalRakeBack.dollarEVTotalPerTourney());
    }

    @GetMapping("/getDollarEVTotalPerTourney3")
    public ResponseEntity<Double> dollarEVTotalPerTourney3() {
        return ResponseEntity.ok(this.dontKnowMyTotalRakeBack.dollarEVTotalPerTourney());
    }

    @GetMapping("/getTourneyPerWeek")
    public ResponseEntity<Integer> tourneyPerWeek() {
        return ResponseEntity.ok(this.dontKnowMyTotalRakeBack.tourneyPerWeek());
    }

    @GetMapping("/getTourneyPerPeriod")
    public ResponseEntity<Integer> tourneyPerPeriod() {
        return ResponseEntity.ok(this.dontKnowMyTotalRakeBack.tourneyPerPeriod());
    }

    @GetMapping("/getRakeBackPercentPerDay")
    public ResponseEntity<Double> rakeBackPercentPerDay() {
        return ResponseEntity.ok(this.dontKnowMyTotalRakeBack.rakeBackPercentPerDay());
    }

    @GetMapping("/getRakeBackPercentPerWeek")
    public ResponseEntity<Double> rakeBackPercentPerWeek() {
        return ResponseEntity.ok(this.dontKnowMyTotalRakeBack.rakeBackPercentPerWeek());
    }

    @GetMapping("/getRakeBackPercentPerPeriod")
    public ResponseEntity<Double> rakeBackPercentPerPeriod() {
        return ResponseEntity.ok(this.dontKnowMyTotalRakeBack.rakeBackPercentPerPeriod());
    }

    @GetMapping("/getRakeBackPercentTotal")
    public ResponseEntity<Double> rakeBackPercentTotal() {
        return ResponseEntity.ok(this.dontKnowMyTotalRakeBack.rakeBackPercentTotal());
    }
}
