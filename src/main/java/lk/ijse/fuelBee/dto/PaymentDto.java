package lk.ijse.fuelBee.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PaymentDto {
    String paymentId;
    String email;
    String sup_email;
    String orderId;
    String method;
    double amount;
    Date date;
    String status;


}
