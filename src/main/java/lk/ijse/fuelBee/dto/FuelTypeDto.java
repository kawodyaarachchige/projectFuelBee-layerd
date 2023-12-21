package lk.ijse.fuelBee.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FuelTypeDto {
    String fuelId;
    String fuelType;
    int qty;
    Double price;
}
