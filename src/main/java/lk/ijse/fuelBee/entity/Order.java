package lk.ijse.fuelBee.entity;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private String orderId;
    private String email;
    private String type;

    private Date date;

    private int tankQty;

    private double price;
    private String status;
}
