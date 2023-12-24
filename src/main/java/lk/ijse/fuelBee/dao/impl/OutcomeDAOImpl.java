package lk.ijse.fuelBee.dao.impl;

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
    public  ArrayList<OutcomeDto> getAllOutcomes() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM Outcome";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ArrayList<OutcomeDto> outcomes = new ArrayList<>();
        ResultSet rs = pstm.executeQuery(sql);

        while (rs.next()){
            outcomes.add(new OutcomeDto(rs.getString(1), rs.getDouble(2), rs.getDate(3)));
        }
        return outcomes;
    }
    @Override
    public  ArrayList<OutcomeDto> getAllOutcomesByDate(java.util.Date startDate, java.util.Date endDate) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM Outcome WHERE date BETWEEN ? AND ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setDate(1, (Date) startDate);
        pstm.setDate(2, (Date) endDate);
        ArrayList<OutcomeDto> outcomes = new ArrayList<>();
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            outcomes.add(new OutcomeDto(rs.getString(1), rs.getDouble(2), rs.getDate(3)));
        }
        return outcomes;
    }
    @Override
    public Map<String, Double> getMonthlyOutcomesTotal() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM Outcome";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ArrayList<OutcomeDto> outcomes = new ArrayList<>();
        ResultSet rs = pstm.executeQuery(sql);

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
}
