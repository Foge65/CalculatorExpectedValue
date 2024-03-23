package team.firestorm.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import team.firestorm.repository.Model;
import team.firestorm.service.room.IPoker;
import team.firestorm.service.room.PokerStars;
import team.firestorm.service.room.Room;
import team.firestorm.service.room.Winamax;

class HyperEVTest {
    private Room room;
    private Model model;
    private Coefficient coefficient;
    private HyperEV hyperEV;

    @Test
    void hyperEV_PokerStars() {
        room = new PokerStars();
        model = new Model();
        coefficient = new Coefficient(model, room);
        hyperEV = new HyperEV(model);

        model.setBuyIn(10);
        model.setTournaments(5000);
        model.setChipsEV(15);
        coefficient.setWinCoefficient(model.getBuyIn());
        coefficient.setLoseCoefficient(model.getBuyIn());

        double hyperEV = this.hyperEV.hyperEV();
        Assertions.assertEquals(-2144.9074999999984, hyperEV);
    }

    @Test
    void hyperEV_Winamax() {
        room = new Winamax();
        model = new Model();
        coefficient = new Coefficient(model, room);
        hyperEV = new HyperEV(model);

        model.setBuyIn(10);
        model.setTournaments(5000);
        model.setChipsEV(30);
        coefficient.setWinCoefficient(model.getBuyIn());
        coefficient.setLoseCoefficient(model.getBuyIn());

        double hyperEV = this.hyperEV.hyperEV();
        Assertions.assertEquals(-1570.5216666666688, hyperEV);
    }

    @Test
    void hyperEV_iPoker() {
        room = new IPoker();
        model = new Model();
        coefficient = new Coefficient(model, room);
        hyperEV = new HyperEV(model);

        model.setBuyIn(10);
        model.setTournaments(5000);
        model.setChipsEV(15);
        coefficient.setWinCoefficient(model.getBuyIn());
        coefficient.setLoseCoefficient(model.getBuyIn());

        double hyperEV = this.hyperEV.hyperEV();
        Assertions.assertEquals(-2447.3333333333403, hyperEV);
    }
}
