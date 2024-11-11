package team.firestorm.service.room;

public interface Room {
    double[] buyIns();

    int[] rakes();

    double[] winCoefficient();

    double[] loseCoefficient();

    double tourneysPerTable();
}
