package team.firestorm.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.firestorm.UserData;
import team.firestorm.room.PokerStars;

class UserControllerTest {
    private UserData userData;

    @BeforeEach
    void setUp() {
        userData = new UserData();
    }

    @Test
    void setRoom() {
        userData.setRoom(new PokerStars());
        Assertions.assertEquals(PokerStars.class, userData.getRoom().getClass());
    }

    @Test
    void setBuyIn() {
        userData.setBuyIn(5);
        Assertions.assertEquals(5, userData.getBuyIn());
    }
}
