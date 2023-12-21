package lk.ijse.fuelBee.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class FuelDto {
    private String machine_id;
    private String fuel_id;

    private String type;

    private String availability;

    private int start_fuel_amount;

    private int day_end_fuel_amount;

    private String date;

}
