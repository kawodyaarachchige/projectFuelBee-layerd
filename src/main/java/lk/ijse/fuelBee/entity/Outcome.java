package lk.ijse.fuelBee.entity;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Outcome {
    private String outcomeId;
    private Double outcomeAmount;
    private Date outcomeDate;
}
