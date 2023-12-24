package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dto.PaymentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentsDAO {
    boolean updatePayment(PaymentDto paymentDto) throws SQLException;
    ArrayList<PaymentDto> getAllPayments() throws SQLException;

}
