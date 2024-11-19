package team.firestorm.model;

import lombok.Data;
import team.firestorm.service.mesh.Mesh;
import team.firestorm.service.mesh.Meshes;
import team.firestorm.service.room.Room;
import team.firestorm.service.room.Rooms;

@Data
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
    private double expDollarEVT;
    private double tables;
    private double rakebackPct;
    private Meshes[] meshes;
    private Mesh mesh;
    private double rollback;
    private double requiredTourneys;
    private double requiredHours;
    private double dollarPerHour;
}
