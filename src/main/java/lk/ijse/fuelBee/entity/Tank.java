package lk.ijse.fuelBee.entity;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Tank {
    private String tankId;
    private String fuelType;
    private int qty;
    private int remainingFuel;
    private int capacityOfWaste;
    private Date date;
}
