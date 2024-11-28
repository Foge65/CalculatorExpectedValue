package team.firestorm.model;

import lombok.Data;
import org.springframework.stereotype.Repository;
import team.firestorm.service.mesh.Mesh;
import team.firestorm.service.mesh.Meshes;
import team.firestorm.service.room.Room;
import team.firestorm.service.room.Rooms;

@Data
@Repository
public class DesiredProfitModel {
    private double desiredProfit;
    private Rooms[] rooms;
    private Room room;
    private double[] buyIns;
    private double buyIn;
    private int[] rakes;
    private double rake;
    private double[] winCoefficients;
    private double winCoefficient;
    private double[] loseCoefficients;
    private double loseCoefficient;
    private double tourneysPerTable;
    private double avgChipsT;
    private double expChipsT;
    private double expEVT;
    private double tables;
    private double rakebackPct;
    private Meshes[] meshes;
    private Mesh mesh;
    private double rollbackPct;
    private double requiredTourneys;
    private double requiredHours;
    private double dollarsPerHour;
}
