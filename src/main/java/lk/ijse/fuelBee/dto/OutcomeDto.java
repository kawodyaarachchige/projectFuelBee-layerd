package lk.ijse.fuelBee.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OutcomeDto {
    private String outcomeId;
    private Double outcomeAmount;
    private Date outcomeDate;
}
