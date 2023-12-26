package lk.ijse.fuelBee.entity;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Payment {
    String paymentId;
    String email;
    String sup_email;
    String orderId;
    String method;
    double amount;
    Date date;
    String status;


}
