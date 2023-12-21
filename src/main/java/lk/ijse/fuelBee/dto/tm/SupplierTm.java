package lk.ijse.fuelBee.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SupplierTm {
    String supId;
    String name;
    String fuelType;
    int contact;
    String address;
    String sup_email;
    Button btnDelete;
}
