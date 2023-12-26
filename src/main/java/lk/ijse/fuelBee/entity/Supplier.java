package lk.ijse.fuelBee.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Supplier {
    String supId;
    String name;
    String fuelType;
    int contact;
    String address;
    String sup_email;
}
