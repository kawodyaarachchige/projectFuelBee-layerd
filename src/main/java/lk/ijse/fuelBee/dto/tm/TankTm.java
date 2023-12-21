package lk.ijse.fuelBee.dto.tm;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TankTm {
    private String tankId;
    private String fuelType;
    private String qty;
    private String remainingFuel;
    private String capacityOfWaste;
    private Date date;
}
