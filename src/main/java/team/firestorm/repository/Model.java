package team.firestorm.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import team.firestorm.service.room.Room;

@Repository
@Getter
@Setter
public class Model {
    private Room room;
    private double buyIn;
    private int rake;
    private int tournaments;
    private double chipsEV;
    private double rakeBack;
    private int tables;
    private double tablesPerHour;
    private double winCoefficient;
    private double loseCoefficient;
    private double dollarsPerHour;
    private double hoursPerDay;
    private double daysPerMonth;
    private double evTotal;
}
