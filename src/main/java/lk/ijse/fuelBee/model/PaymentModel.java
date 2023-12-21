package lk.ijse.fuelBee.model;

import lk.ijse.fuelBee.controller.ProfitFormController;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.PaymentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentModel {
    public static boolean savePayment(PaymentDto paymentDto) throws SQLException {
       Connection connection = Dbconnection.getInstance().getConnection();
       String sql = "INSERT INTO Payment VALUES(?,?,?,?,?,?,?,?)";
       PreparedStatement pstm = connection.prepareStatement(sql);
       pstm.setString(1, paymentDto.getPaymentId());
       pstm.setString(2, paymentDto.getEmail());
       pstm.setString(3, paymentDto.getSup_email());
       pstm.setString(4, paymentDto.getMethod());
       pstm.setDouble(5, paymentDto.getAmount());
       pstm.setDate(6, new java.sql.Date(paymentDto.getDate().getTime()));
       pstm.setString(7, paymentDto.getStatus());
       pstm.setString(8, paymentDto.getOrderId());

       int isSaved = pstm.executeUpdate();
       if (isSaved > 0) {
           return true;
       }else{
           return false;
       }
    }
    public static boolean updatePayment(PaymentDto paymentDto) throws SQLException {
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
    public static ArrayList<PaymentDto> getAllPayments() throws SQLException {
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
    public static boolean deletePayment(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql="DELETE FROM Payment WHERE pay_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        int isDeleted = pstm.executeUpdate();
        if(isDeleted>0){
            return true;
        }else{
            return false;
        }
    }

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
