package lk.ijse.fuelBee.bo.custom.impl;

import lk.ijse.fuelBee.bo.custom.OutcomeBO;
import lk.ijse.fuelBee.dao.DAOFactory;
import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.OutcomeDAO;
import lk.ijse.fuelBee.dao.custom.PaymentsDAO;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.OutcomeDto;
import lk.ijse.fuelBee.entity.Outcome;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class OutcomeBOImpl implements OutcomeBO {
     OutcomeDAO outcomeDAO = (OutcomeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.OUTCOME);
     PaymentsDAO paymentsDAO = (PaymentsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.PAYMENTS);
   @Override
    public ArrayList<OutcomeDto> getAllOutcomes() throws SQLException, ClassNotFoundException {
        ArrayList<Outcome> outcomes = outcomeDAO.getAll();
        ArrayList<OutcomeDto> outcomeDTOS = new ArrayList<>();
        for (Outcome outcome : outcomes) {
            outcomeDTOS.add(new OutcomeDto(outcome.getOutcomeId(), outcome.getOutcomeAmount(), outcome.getOutcomeDate()));
        }
        return outcomeDTOS;
    }

    @Override
    public boolean saveOutcome(OutcomeDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateOutcome(OutcomeDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteOutcome(String id) throws SQLException, ClassNotFoundException {
        return false;
       // return  SQLUtil.execute("DELETE FROM Outcome WHERE outcome_id=?", id);
    }


    @Override
    public  ArrayList<OutcomeDto> getAllOutcomesByDate(java.util.Date startDate, java.util.Date endDate) throws SQLException, ClassNotFoundException {
      ArrayList<Outcome> outcomes = outcomeDAO.getAllByDate(startDate, endDate);
      ArrayList<OutcomeDto> outcomeDTOS = new ArrayList<>();
      for (Outcome outcome : outcomes) {
          outcomeDTOS.add(new OutcomeDto(outcome.getOutcomeId(), outcome.getOutcomeAmount(), outcome.getOutcomeDate()));
      }
      return outcomeDTOS;

    }
    @Override
    public Map<String, Double> getMonthlyOutcomesTotal() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT * FROM Outcome");
        ArrayList<OutcomeDto> outcomes = new ArrayList<>();
        Map<String, Double> monthlyTotals = new HashMap<>();
        while (rs.next()) {
            String month = getMonthFromDate(rs.getDate(3));
            monthlyTotals.merge(month, rs.getDouble(2), Double::sum);
        }
        return monthlyTotals;
    }
  @Override
    public  String getMonthFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        return String.format("%02d", month);
    }
   @Override
    public  boolean deleteOutcomes(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            //boolean isDeleted= outcomeDao.delete(id);
            boolean isDeleted = outcomeDAO.delete(id);
            boolean isDeleted2 = paymentsDAO.delete(id);
           // boolean isDeleted = SQLUtil.execute("DELETE FROM Outcome WHERE outcome_id=?", id);
           // boolean isDeleted2 = SQLUtil.execute("DELETE FROM Payment WHERE outcome_id=?", id);
            if ((isDeleted ) && (isDeleted2 )) {
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
