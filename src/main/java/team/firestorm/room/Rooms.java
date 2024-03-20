package team.firestorm.room;

import java.util.ArrayList;
import java.util.List;

public final class Rooms {
    public static List<Room> rooms() {
        return new ArrayList<>(List.of(
                new PokerStars(),
                new Winamax(),
                new IPoker())
        );
    }
}
