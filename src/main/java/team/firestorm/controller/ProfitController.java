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

    @GetMapping("/needTourneys1")
    public ResponseEntity<Double> needTourneys1() {
        return ResponseEntity.ok(profitCalc.requiredTourneys1());
    }

    @GetMapping("/needTourneys2")
    public ResponseEntity<Double> needTourneys2() {
        return ResponseEntity.ok(profitCalc.requiredTourneys2());
    }

    @GetMapping("/expEVT1")
    public ResponseEntity<Double> expEVT1() {
        return ResponseEntity.ok(profitCalc.dollarEVPerTourney1());
    }

    @GetMapping("/expEVT2")
    public ResponseEntity<Double> expEVT2() {
        return ResponseEntity.ok(profitCalc.dollarEVPerTourney2());
    }

    @GetMapping("/needHours")
    public ResponseEntity<Double> needHours() {
        return ResponseEntity.ok(profitCalc.requiredHours());
    }

    @GetMapping("/dollarsPerHour1")
    public ResponseEntity<Double> dollarsPerHour1() {
        return ResponseEntity.ok(profitCalc.dollarsPerHour1());
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

    @GetMapping("/getRollbackPercent1")
    public ResponseEntity<Double> rollbackPercent1() {
        return ResponseEntity.ok(profitCalc.rollbackPercent1());
    }

    @GetMapping("/getRollbackPercent2")
    public ResponseEntity<Double> rollbackPercent2() {
        return ResponseEntity.ok(profitCalc.rollbackPercent2());
    }

    @GetMapping("/profitAfterRollback1")
    public ResponseEntity<Double> profitAfterRollback1() {
        return ResponseEntity.ok(profitCalc.profitAfterRollback1());
    }

    @GetMapping("/profitAfterRollback2")
    public ResponseEntity<Double> profitAfterRollback2() {
        return ResponseEntity.ok(profitCalc.profitAfterRollback2());
    }

    @PostMapping("/desiredProfit")
    public void desiredProfit(@RequestParam("profit") double profit) {
        modelRepository.setDesiredProfit(profit);
    }

    @PostMapping("/expChipsT1")
    public void expChipsT1(@RequestParam("chips") double chips) {
        modelRepository.setExpChipsT1(chips);
    }

    @PostMapping("/expChipsT2")
    public void expChipsT2(@RequestParam("chips") double chips) {
        modelRepository.setExpChipsT2(chips);
    }

    @PostMapping("/tables1")
    public void tables1(@RequestParam("tables") int tables) {
        modelRepository.setTables1(tables);
    }

    @PostMapping("/tables2")
    public void tables2(@RequestParam("tables") int tables) {
        modelRepository.setTables2(tables);
    }

    @PostMapping("/haveHours")
    public void haveHours(@RequestParam("hours") int hours) {
        modelRepository.setHaveHours(hours);
    }

    @PostMapping("/rakebackPct1")
    public void rakebackPct1(@RequestParam("rakeback") double rakeback) {
        modelRepository.setRakebackPercent1(rakeback);
    }

    @PostMapping("/rakebackPct2")
    public void rakebackPct2(@RequestParam("rakeback") double rakeback) {
        modelRepository.setRakebackPercent2(rakeback);
    }
}
