package team.firestorm.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import team.firestorm.repository.Model;
import team.firestorm.service.room.PokerStars;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @MockBean
    private Model model;

    @BeforeEach
    void setUp() {
        model = new Model();
    }

    @Test
    void setRoom() {
        model.setRoom(new PokerStars());
        Assertions.assertEquals(PokerStars.class, model.getRoom().getClass());
    }

    @Test
    void setBuyIn() {
        model.setBuyIn(5);
        Assertions.assertEquals(5, model.getBuyIn());

        model.setWinCoefficient(1.7330501);
        Assertions.assertEquals(1.7330501, model.getWinCoefficient());

        model.setLoseCoefficient(-0.9871249);
        Assertions.assertEquals(-0.9871249, model.getLoseCoefficient());

        // Проверить, что когда пользователь выберет байин - установятся
        // соответствующие коэффициенты

    }
}
