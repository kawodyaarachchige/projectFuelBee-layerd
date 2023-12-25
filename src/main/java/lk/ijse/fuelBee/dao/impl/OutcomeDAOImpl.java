package lk.ijse.fuelBee.dao.impl;

import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.OutcomeDAO;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.OutcomeDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class OutcomeDAOImpl implements OutcomeDAO {
    @Override
    public  ArrayList<OutcomeDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT * FROM Outcome");
        ArrayList<OutcomeDto> outcomes = new ArrayList<>();
        while (rs.next()){
            outcomes.add(new OutcomeDto(rs.getString(1), rs.getDouble(2), rs.getDate(3)));
        }
        return outcomes;
    }

    @Override
    public boolean save(OutcomeDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OutcomeDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OutcomeDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OutcomeDto get(String id) throws SQLException {
        return null;
    }

    @Override
    public  ArrayList<OutcomeDto> getAllOutcomesByDate(java.util.Date startDate, java.util.Date endDate) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT * FROM Outcome WHERE date BETWEEN ? AND ?", startDate, endDate);
        ArrayList<OutcomeDto> outcomes = new ArrayList<>();
        while(rs.next()){
            outcomes.add(new OutcomeDto(rs.getString(1), rs.getDouble(2), rs.getDate(3)));
        }
        return outcomes;
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
    public  boolean deleteOutcome(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            boolean isDeleted = SQLUtil.execute("DELETE FROM Outcome WHERE outcome_id=?", id);
            boolean isDeleted2 = SQLUtil.execute("DELETE FROM Payment WHERE outcome_id=?", id);
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
