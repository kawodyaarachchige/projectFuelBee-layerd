package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dao.CrudDAO;
import lk.ijse.fuelBee.dto.PaymentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentsDAO extends CrudDAO<PaymentDto> {
   // boolean updatePayment(PaymentDto paymentDto) throws SQLException, ClassNotFoundException;
  //  ArrayList<PaymentDto> getAllPayments() throws SQLException, ClassNotFoundException;
   boolean confirmPayment(PaymentDto paymentDto) throws SQLException;

}
