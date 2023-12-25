package lk.ijse.fuelBee.dao.impl;

import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.PaymentsDAO;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.PaymentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentsDAOImpl implements PaymentsDAO {
   @Override
    public boolean updatePayment(PaymentDto paymentDto) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("UPDATE Payment SET email=?, sup_email=?, method=?, amount=?, date=?, status=?,order_id=? WHERE pay_id=?", paymentDto.getEmail(), paymentDto.getSup_email(), paymentDto.getMethod(), paymentDto.getAmount(), paymentDto.getDate(), paymentDto.getStatus(), paymentDto.getOrderId(), paymentDto.getPaymentId());

    }
    @Override
    public ArrayList<PaymentDto> getAllPayments() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT * FROM Payment");
        ArrayList<PaymentDto> payments = new ArrayList<>();
        while(rs.next()){
            payments.add(new PaymentDto(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getDouble(6),
                    rs.getDate(7),
                    rs.getString(8)
            ));
        }return payments;

    }
}
