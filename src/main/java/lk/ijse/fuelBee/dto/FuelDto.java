package lk.ijse.fuelBee.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FuelDto {
    private String fuelId;
    private String fuelType;
    private int qty;
    private Double price;
}
