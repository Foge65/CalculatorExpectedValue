package team.firestorm.service.room;

public interface Room {
    String name();

    double[] buyIns();

    int[] rakes();

    double[] winCoefficient();

    double[] loseCoefficient();

    double tourneysPerTable();
}
