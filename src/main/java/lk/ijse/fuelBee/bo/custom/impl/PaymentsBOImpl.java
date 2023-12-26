package lk.ijse.fuelBee.bo.custom.impl;

import lk.ijse.fuelBee.bo.custom.PaymentsBO;
import lk.ijse.fuelBee.controller.ProfitFormController;
import lk.ijse.fuelBee.dao.DAOFactory;
import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.OrderDAO;
import lk.ijse.fuelBee.dao.custom.OutcomeDAO;
import lk.ijse.fuelBee.dao.custom.PaymentsDAO;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.PaymentDto;
import lk.ijse.fuelBee.entity.Order;
import lk.ijse.fuelBee.entity.Outcome;
import lk.ijse.fuelBee.entity.Payment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentsBOImpl implements PaymentsBO {

    PaymentsDAO paymentsDAO = (PaymentsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.PAYMENTS);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER);

    OutcomeDAO outcomeDAO = (OutcomeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.OUTCOME);
   @Override
    public boolean updatePayment(PaymentDto dto) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("UPDATE Payment SET email=?, sup_email=?, method=?, amount=?, date=?, status=?,order_id=? WHERE pay_id=?", dto.getEmail(), dto.getSup_email(), dto.getMethod(), dto.getAmount(), dto.getDate(), dto.getStatus(), dto.getOrderId(), dto.getPaymentId());

    }

    @Override
    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Payment WHERE outcome_id=?", id);
    }

@Override
   public ArrayList<PaymentDto> getAllPayments() throws SQLException, ClassNotFoundException {
       ArrayList<Payment> payments = paymentsDAO.getAll();
       ArrayList<PaymentDto> paymentDTOS = new ArrayList<>();
       for (Payment payment : payments) {
           paymentDTOS.add(new PaymentDto(
                   payment.getPaymentId(),
                   payment.getEmail(),
                   payment.getSup_email(),
                   payment.getOrderId(),
                   payment.getMethod(),
                   payment.getAmount(),
                   payment.getDate(),
                   payment.getStatus()
           ));
       }
       return paymentDTOS;
    }

    @Override
    public boolean savePayment(PaymentDto dto) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute("INSERT INTO Payment VALUES(?,?,?,?,?,?,?,?)", dto.getPaymentId(), dto.getEmail(), dto.getSup_email(), dto.getOrderId(), dto.getMethod(), dto.getAmount(), dto.getDate(), dto.getStatus());
    }
   @Override
    public boolean confirmPayment(PaymentDto dto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            boolean isSaved = paymentsDAO.save(new Payment(dto.getPaymentId(), dto.getEmail(), dto.getSup_email(), dto.getOrderId(), dto.getMethod(), dto.getAmount(), dto.getDate(), dto.getStatus()));
           // boolean isSaved = SQLUtil.execute("INSERT INTO Payment VALUES(?,?,?,?,?,?,?,?)", paymentDto.getPaymentId(), paymentDto.getEmail(), paymentDto.getSup_email(), paymentDto.getOrderId(), paymentDto.getMethod(), paymentDto.getAmount(), paymentDto.getDate(), paymentDto.getStatus());
            System.out.println("Method Value: " + dto.getMethod());
          //  PaymentsDto paymentDto = new PaymentsDto(dto.getOrderId();
            boolean isUpdated = orderDAO.updateOrderStatus((dto.getOrderId()), "PAID");
           // boolean isUpdated = SQLUtil.execute("UPDATE Orders SET status='PAID' WHERE order_id=?", paymentDto.getOrderId());
           // boolean isOutcomeSaved = SQLUtil.execute("INSERT INTO Outcome VALUES(?,?,?)",paymentDto.getPaymentId(), ProfitFormController.generateOutcomeId(),paymentDto.getAmount() ,new java.sql.Date(paymentDto.getDate().getTime()));
            //boolean isOutcomeSaved = outcomeDAO.save(new PaymentsDto(dto.getPaymentId(), ProfitFormController.generateOutcomeId(), dto.getAmount(), new java.sql.Date(dto.getDate().getTime())));
           boolean isOutcomeSaved = outcomeDAO.save(new Outcome(ProfitFormController.generateOutcomeId(), dto.getAmount(), new java.sql.Date(dto.getDate().getTime())));
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
