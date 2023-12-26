package lk.ijse.fuelBee.entity;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Income {
    private String incomeId;
    private Double amount;
    private Date date;
}
