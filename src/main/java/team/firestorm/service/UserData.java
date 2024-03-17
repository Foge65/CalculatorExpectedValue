package team.firestorm.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class UserData {
    private int tournaments;
    private double chipsEV;
    private double rakeBack;
    private int numberTables;
    private double numberTablesPerHour;
}
