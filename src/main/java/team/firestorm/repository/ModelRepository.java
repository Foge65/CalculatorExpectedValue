package team.firestorm.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import team.firestorm.service.mesh.Mesh;
import team.firestorm.service.room.Room;

@Repository
@Getter
@Setter
public class ModelRepository {
    private Room room;
    private double[] buyIns;
    private int[] rakes;
    private double[] winCoefficients;
    private double[] loseCoefficients;

    private double buyIn;
    private double rake;
    private int tourneyPerDay;
    private int tourneyPerPeriod;
    private double chipsEVFromTourney;
    private double winCoefficient;
    private double loseCoefficient;
    private double dollarsPerHour;
    private double hoursPerDay;
    private int daysPerWeek;
    private int daysPerMonth;
    private double dollarsEVPerTourney;
    private double rakeBackDollarsPerDay;
    private double rakeBackDollarsPerWeek;
    private double rakeBackDollarsPerPeriod;
    private double rakeBackTotal;
    private int weeksPerPeriod;
    private Mesh mesh;

    private double desiredProfit;
    private int expChipsT;
    private double expEVT;
    private int tables;
    private double rakebackPercent;
    private double requiredTourneys;
    private double tourneysPerTable;
    private int rollback;
    private double requiredHours;

    private int haveHours;
    private double tourneys;
    private double estimatedExpectation;
    private double profitAfterRollback;
}
