package team.firestorm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team.firestorm.repository.ModelRepository;
import team.firestorm.service.mesh.Meshes;
import team.firestorm.service.room.Rooms;

@RestController
@AllArgsConstructor
public class GetController {
    private ModelRepository modelRepository;

    @GetMapping("/getRooms")
    public ResponseEntity<Rooms[]> rooms() {
        return ResponseEntity.ok(Rooms.values());
    }

    @GetMapping("/getBuyIns1")
    public ResponseEntity<double[]> buyIns1() {
        return ResponseEntity.ok(this.modelRepository.getBuyIns1());
    }

    @GetMapping("/getBuyIns2")
    public ResponseEntity<double[]> buyIns2() {
        return ResponseEntity.ok(this.modelRepository.getBuyIns2());
    }

    @GetMapping("/getMeshes")
    public ResponseEntity<Meshes[]> meshes() {
        return ResponseEntity.ok(Meshes.values());
    }
}
