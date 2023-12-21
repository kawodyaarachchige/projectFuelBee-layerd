package lk.ijse.fuelBee.dto.tm;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OutcomeTm {
    private String outcomeId;
    private Double outcomeAmount;
    private Date outcomeDate;
}
