package lk.ijse.fuelBee.bo.custom;

import lk.ijse.fuelBee.bo.SuperBO;
import lk.ijse.fuelBee.dao.CrudDAO;
import lk.ijse.fuelBee.dto.PaymentDto;
import lk.ijse.fuelBee.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentsBO extends SuperBO {
    boolean updatePayment(PaymentDto dto) throws SQLException, ClassNotFoundException;

    boolean deletePayment(String id) throws SQLException, ClassNotFoundException;

    boolean savePayment(PaymentDto dto) throws SQLException, ClassNotFoundException;

    // boolean updatePayment(PaymentDto paymentDto) throws SQLException, ClassNotFoundException;
   ArrayList<PaymentDto> getAllPayments() throws SQLException, ClassNotFoundException;
   boolean confirmPayment(PaymentDto paymentDto) throws SQLException;

}
