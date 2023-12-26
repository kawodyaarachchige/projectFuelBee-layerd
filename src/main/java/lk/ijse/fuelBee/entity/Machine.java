package lk.ijse.fuelBee.entity;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Machine {
    private String machineId;
    private String fuelId;
    private String fuelType;
    private String availability;
    private int startFuelAmount;
    private int endFuelAmount;
    private Date date;
}
