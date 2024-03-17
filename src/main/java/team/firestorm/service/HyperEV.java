package team.firestorm.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team.firestorm.entity.Room;

@Service
@AllArgsConstructor
public class HyperEV {
    private Room room;
    private UserData userData;

    public double calculateCoefficients() {
        double[] buyIns = room.buyIns();
        int tournaments = userData.getTournaments();
        double chipsEV = userData.getChipsEV();
        double[] winCoefficients = room.winCoefficient();
        double[] loseCoefficients = room.loseCoefficient();

        return buyIns[0] * tournaments * (((500 + chipsEV) / 1500) * winCoefficients[0]
                + (1 - ((500 + chipsEV) / 1500)) * loseCoefficients[0]);
    }
}
