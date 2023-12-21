package lk.ijse.fuelBee.dto.tm;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class IncomeTm {
    private String incomeId;
    private Double amount;
    private Date date;
}
