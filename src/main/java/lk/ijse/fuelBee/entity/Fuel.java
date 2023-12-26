package lk.ijse.fuelBee.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Fuel {
    String fuelId;
    String fuelType;
    int qty;
    Double price;
}
