package lk.ijse.fuelBee.dao.impl;

import lk.ijse.fuelBee.controller.ProfitFormController;
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
    public boolean update(PaymentDto paymentDto) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("UPDATE Payment SET email=?, sup_email=?, method=?, amount=?, date=?, status=?,order_id=? WHERE pay_id=?", paymentDto.getEmail(), paymentDto.getSup_email(), paymentDto.getMethod(), paymentDto.getAmount(), paymentDto.getDate(), paymentDto.getStatus(), paymentDto.getOrderId(), paymentDto.getPaymentId());

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public PaymentDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public PaymentDto get(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<PaymentDto> getAll() throws SQLException, ClassNotFoundException {
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

    @Override
    public boolean save(PaymentDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }
   @Override
    public boolean confirmPayment(PaymentDto paymentDto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            boolean isSaved = SQLUtil.execute("INSERT INTO Payment VALUES(?,?,?,?,?,?,?,?)", paymentDto.getPaymentId(), paymentDto.getEmail(), paymentDto.getSup_email(), paymentDto.getOrderId(), paymentDto.getMethod(), paymentDto.getAmount(), paymentDto.getDate(), paymentDto.getStatus());
            System.out.println("Method Value: " + paymentDto.getMethod());
            boolean isUpdated = SQLUtil.execute("UPDATE Orders SET status='PAID' WHERE order_id=?", paymentDto.getOrderId());
            boolean isOutcomeSaved = SQLUtil.execute("INSERT INTO Outcome VALUES(?,?,?)",paymentDto.getPaymentId(), ProfitFormController.generateOutcomeId(),paymentDto.getAmount() ,new java.sql.Date(paymentDto.getDate().getTime()));

            if ((isSaved) && (isUpdated) && (isOutcomeSaved)) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                System.out.println("Failed to confirm payment");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
