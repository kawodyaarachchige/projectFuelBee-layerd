package lk.ijse.fuelBee.dto.tm;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class MachineTm {
    private String machineId;
    private String fuelId;
    private String fuelType;
    private String availability;
    private String startFuelAmount;
    private String endFuelAmount;
    private Date date;
}
