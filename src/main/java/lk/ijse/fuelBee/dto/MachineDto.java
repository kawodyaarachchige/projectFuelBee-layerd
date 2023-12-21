package lk.ijse.fuelBee.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MachineDto {
    private String machineId;
    private String fuelId;
    private String fuelType;
    private String availability;
    private int startFuelAmount;
    private int endFuelAmount;
    private Date date;
}
