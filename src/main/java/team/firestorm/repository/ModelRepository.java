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
    private Room room1;
    private Room room2;
    private double[] buyIns1;
    private double[] buyIns2;
    private int[] rakes1;
    private int[] rakes2;
    private double[] winCoefficients1;
    private double[] winCoefficients2;
    private double[] loseCoefficients1;
    private double[] loseCoefficients2;

    private double buyIn1;
    private double buyIn2;
    private double rake1;
    private double rake2;
    private int tourneyPerDay;
    private int tourneyPerPeriod;
    private double chipsEVFromTourney;
    private double winCoefficient1;
    private double winCoefficient2;
    private double loseCoefficient1;
    private double loseCoefficient2;
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
    private Mesh mesh1;
    private Mesh mesh2;

    private double desiredProfit;
    private double expChipsT1;
    private double expChipsT2;
    private double expEVT1;
    private double expEVT2;
    private int tables1;
    private int tables2;
    private double rakebackPercent1;
    private double rakebackPercent2;
    private double requiredTourneys;
    private double tourneysPerTable1;
    private double tourneysPerTable2;
    private int rollback1;
    private int rollback2;
    private double requiredHours;

    private int haveHours;
    private double tourneys;
    private double estimatedExpectation;
    private double profitAfterRollback;
}
