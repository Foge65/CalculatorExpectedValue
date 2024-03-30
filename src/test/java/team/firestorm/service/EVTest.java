package team.firestorm.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import team.firestorm.repository.Model;
import team.firestorm.service.room.IPoker;
import team.firestorm.service.room.PokerStars;
import team.firestorm.service.room.Room;
import team.firestorm.service.room.Winamax;

class EVTest {
    private Model model;
    private Coefficient coefficient;
    private EV ev;

    @Test
    void hyperEV_PokerStars() {
        initRoom(new PokerStars());

        setData();

        double hyperEV = this.ev.hyperEV();
        Assertions.assertEquals(-769.8199999999988, hyperEV);
    }

    @Test
    void hyperEV_Winamax() {
        initRoom(new Winamax());

        setData();

        double hyperEV = this.ev.hyperEV();
        Assertions.assertEquals(-1570.5216666666688, hyperEV);
    }

    @Test
    void hyperEV_iPoker() {
        initRoom(new IPoker());

        setData();

        double hyperEV = this.ev.hyperEV();
        Assertions.assertEquals(-1062.3333333333374, hyperEV);
    }

    private void initRoom(Room room) {
        model = new Model();
        coefficient = new Coefficient(model, room);
        ev = new EV(model);
    }

    private void setData() {
        model.setBuyIn(10);
        model.setTourneyPerPeriod(5000);
        model.setChipsEVFromTourney(30);
        coefficient.setWinCoefficient(model.getBuyIn());
        coefficient.setLoseCoefficient(model.getBuyIn());
    }
}
