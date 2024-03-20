package team.firestorm;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import team.firestorm.room.Room;

@Service
@Getter
@Setter
public class UserData {
    private Room room;
    private double buyIn;
    private int rake;
    private int tournaments;
    private double chipsEV;
    private double rakeBack;
    private int numberTables;
    private double numberTablesPerHour;
}
