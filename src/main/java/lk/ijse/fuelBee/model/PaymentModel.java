package lk.ijse.fuelBee.model;

import lk.ijse.fuelBee.dao.impl.PaymentsDAOImpl;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.PaymentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentModel {

    public static boolean confirmPayment(PaymentDto paymentDto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO Payment VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, paymentDto.getPaymentId());
            pstm.setString(2, paymentDto.getEmail());
            pstm.setString(3, paymentDto.getSup_email());
            pstm.setString(4, paymentDto.getOrderId());
            pstm.setString(5, paymentDto.getMethod());
            pstm.setDouble(6, paymentDto.getAmount());
            pstm.setDate(7, new java.sql.Date(paymentDto.getDate().getTime()));
            pstm.setString(8, paymentDto.getStatus());

            int isSaved = pstm.executeUpdate();
            System.out.println("Method Value: " + paymentDto.getMethod());

            String sql1 = "UPDATE Orders SET status='PAID' WHERE order_id=?";
            PreparedStatement pstm1 = connection.prepareStatement(sql1);
            pstm1.setString(1, paymentDto.getOrderId());
            int isUpdated = pstm1.executeUpdate();

            String sql2 = "INSERT INTO Outcome VALUES(?,?,?)";
            PreparedStatement pstm2 = connection.prepareStatement(sql2);
            pstm2.setString(1, paymentDto.getPaymentId());
            //pstm2.setString(1, ProfitFormController.generateOutcomeId());
            pstm2.setDouble(2, paymentDto.getAmount());
            pstm2.setDate(3, new java.sql.Date(paymentDto.getDate().getTime()));
            int isOutcomeSaved = pstm2.executeUpdate();

            if ((isSaved > 0) && (isUpdated > 0) && (isOutcomeSaved > 0)) {
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

    public static boolean deleteOutcome(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            String sql1 = "DELETE FROM Outcome WHERE outcome_id=?";
            PreparedStatement pstm1 = connection.prepareStatement(sql1);
            pstm1.setString(1, id);
            int isDeleted = pstm1.executeUpdate();


            String sql2 = "DELETE FROM Payment WHERE pay_id=?";
            PreparedStatement pstm2 = connection.prepareStatement(sql2);
            pstm2.setString(1, id);
            int isDeleted2 = pstm2.executeUpdate();

            if ((isDeleted > 0) && (isDeleted2 > 0)) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                System.out.println("Failed to delete Outcome");
                return false;
            }
    }catch (Exception e) {
        e.printStackTrace();
        return false;
    } finally {
        connection.setAutoCommit(true);
    }

    }


}
