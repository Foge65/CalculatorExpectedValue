package team.firestorm.dto;

import lombok.Data;

@Data
public class DesiredProfitRequestDTO {
    private double desiredProfit;
    private double buyIn;
    private double expChipsT;
    private double tables;
    private double rakebackPct;
}
