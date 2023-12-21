package lk.ijse.fuelBee.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class IncomeDto {
    private String incomeId;
    private Double amount;
    private Date date;
}
