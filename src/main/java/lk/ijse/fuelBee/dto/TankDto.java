package lk.ijse.fuelBee.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TankDto {
    private String tankId;
    private String fuelType;
    private int qty;
    private int remainingFuel;
    private int capacityOfWaste;
    private Date date;
}
