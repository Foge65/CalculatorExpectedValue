package team.firestorm.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import team.firestorm.service.mesh.Mesh;
import team.firestorm.service.room.Room;

@Repository
@Getter
@Setter
public class Model {
    private Room room;
    private double buyIn;
    private int rake;
    private int tourneyPerPeriod;
    private double chipsEVFromTourney;
    private double rakeBackTotal;
    private int tables;
    private double tablesPerHour;
    private double winCoefficient;
    private double loseCoefficient;
    private double dollarsPerHour;
    private double hoursPerDay;
    private int daysPerWeek;
    private int daysPerMonth;
    private double evTotal;
    private double dollarsEVPerTourney;
    private double rakeBackDollarsPerDay;
    private double rakeBackDollarsPerWeek;
    private Mesh mesh;
    private int tourneyPerDay;
    private int tourneyPerWeek;
    private double rakeBackPercentPerDay;
}
