package lk.ijse.fuelBee.dao;

import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.PaymentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentsDAOImpl {
    public boolean updatePayment(PaymentDto paymentDto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "UPDATE Payment SET email=?, sup_email=?, method=?, amount=?, date=?, status=?,order_id=? WHERE pay_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, paymentDto.getEmail());
        pstm.setString(2, paymentDto.getSup_email());
        pstm.setString(3, paymentDto.getMethod());
        pstm.setDouble(4, paymentDto.getAmount());
        pstm.setDate(5, new java.sql.Date(paymentDto.getDate().getTime()));
        pstm.setString(6, paymentDto.getStatus());
        pstm.setString(7, paymentDto.getOrderId());
        pstm.setString(8, paymentDto.getPaymentId());

        int isUpdated = pstm.executeUpdate();
        if (isUpdated > 0) {
            return true;
        }else{
            return false;
        }
    }
    public ArrayList<PaymentDto> getAllPayments() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM Payment";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ArrayList<PaymentDto> payments = new ArrayList<>();
        ResultSet rs = pstm.executeQuery(sql);

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
