package lk.ijse.fuelBee.dto.tm;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PaymentTm {
    String paymentId;
    String email;
    String orderId;
    String method;
    double amount;
    Date date;
    String status;
    String sup_email;
}
