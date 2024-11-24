package team.firestorm.dto;

import lombok.Data;

@Data
public class HaveHoursRequestDTO {
    private int modelId;
    private double haveHours;
    private double buyIn;
    private double expChipsT;
    private double tables;
    private double rakebackPct;
}
