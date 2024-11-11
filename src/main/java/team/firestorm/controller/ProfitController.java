package team.firestorm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.firestorm.domain.ProfitCalc;
import team.firestorm.repository.ModelRepository;

@RestController
@RequiredArgsConstructor
public class ProfitController {
    private final ProfitCalc profitCalc;
    private final ModelRepository modelRepository;

    @GetMapping("/needTourneys")
    public ResponseEntity<Double> needTourneys() {
        return ResponseEntity.ok(profitCalc.requiredTourneys());
    }

    @GetMapping("/expEVT")
    public ResponseEntity<Double> expEVT() {
        return ResponseEntity.ok(modelRepository.getExpEVT());
    }

    @GetMapping("/needHours")
    public ResponseEntity<Double> needHours() {
        return ResponseEntity.ok(profitCalc.requiredHours());
    }

    @GetMapping("/dollarsPerHour")
    public ResponseEntity<Double> dollarsPerHour() {
        return ResponseEntity.ok(profitCalc.dollarsPerHour());
    }

    @GetMapping("/tourneys")
    public ResponseEntity<Double> tourneys() {
        return ResponseEntity.ok(profitCalc.tourneys());
    }

    @GetMapping("/estimatedExpectation")
    public ResponseEntity<Double> estimatedExpectation() {
        return ResponseEntity.ok(profitCalc.estimatedExpectation());
    }

    @GetMapping("/dollarsPerHour2")
    public ResponseEntity<Double> dollarsPerHour2() {
        return ResponseEntity.ok(profitCalc.dollarsPerHour2());
    }

    @GetMapping("/getRollbackPercent")
    public ResponseEntity<Integer> rollbackPercent() {
        return ResponseEntity.ok(modelRepository.getRollback());
    }

    @GetMapping("/profitAfterRollback")
    public ResponseEntity<Double> profitAfterRollback() {
        return ResponseEntity.ok(profitCalc.profitAfterRollback());
    }

    @PostMapping("/desiredProfit")
    public void desiredProfit(@RequestParam("profit") double profit) {
        modelRepository.setDesiredProfit(profit);
    }

    @PostMapping("/expChipsT")
    public void expChipsT(@RequestParam("chips") int chips) {
        modelRepository.setExpChipsT(chips);
    }

    @PostMapping("/tables")
    public void tables(@RequestParam("tables") int tables) {
        modelRepository.setTables(tables);
    }

    @PostMapping("/haveHours")
    public void haveHours(@RequestParam("haveHours") int haveHours) {
        modelRepository.setHaveHours(haveHours);
    }
}
