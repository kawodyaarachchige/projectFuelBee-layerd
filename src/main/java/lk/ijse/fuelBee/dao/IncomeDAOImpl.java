package lk.ijse.fuelBee.dao;

import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.IncomeDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
;

public class IncomeDAOImpl {
    public static ArrayList<IncomeDto> getAllIncomes() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM Income";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ArrayList<IncomeDto> incomes = new ArrayList<>();
        ResultSet rs = pstm.executeQuery(sql);

        while (rs.next()) {
            incomes.add(new IncomeDto(rs.getString(1), rs.getDouble(2), rs.getDate(3)));
        }
        return incomes;
    }
    public static ArrayList<IncomeDto> getAllIncomesByDate(java.util.Date startDate, java.util.Date endDate) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM Income WHERE date BETWEEN ? AND ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setDate(1, (Date) startDate);
        pstm.setDate(2, (Date) endDate);
        ArrayList<IncomeDto> incomes = new ArrayList<>();
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            incomes.add(new IncomeDto(rs.getString(1), rs.getDouble(2), rs.getDate(3)));
        }
        return incomes;
    }
    public static Map<String, Double> getMonthlyIncomesTotal() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM Income";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ArrayList<IncomeDto> incomes = new ArrayList<>();
        ResultSet rs = pstm.executeQuery(sql);

        Map<String, Double> monthlyTotals = new HashMap<>();
        while (rs.next()) {
            String month = getMonthFromDate(rs.getDate(3));
            monthlyTotals.merge(month, rs.getDouble(2), Double::sum);
        }
        return monthlyTotals;
    }
    private static String getMonthFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        return String.format("%02d", month);
    }
}
