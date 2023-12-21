package lk.ijse.fuelBee.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SupplierDto {
    String supId;
    String name;
    String fuelType;
    int contact;
    String address;
    String sup_email;
}
