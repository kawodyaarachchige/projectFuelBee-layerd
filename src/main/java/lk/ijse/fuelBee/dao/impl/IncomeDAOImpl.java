package lk.ijse.fuelBee.dao.impl;

import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.IncomeDAO;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.IncomeDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
;

public class IncomeDAOImpl implements IncomeDAO {
   @Override
    public  ArrayList<IncomeDto> getAllIncomes() throws SQLException, ClassNotFoundException {
       ResultSet rs = SQLUtil.execute("SELECT * FROM Income");
       ArrayList<IncomeDto> incomes = new ArrayList<>();
        while (rs.next()) {
            incomes.add(new IncomeDto(rs.getString(1), rs.getDouble(2), rs.getDate(3)));
        }
        return incomes;
    }
    @Override
    public  ArrayList<IncomeDto> getAllIncomesByDate(java.util.Date startDate, java.util.Date endDate) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT * FROM Income WHERE date BETWEEN ? AND ?", startDate, endDate);
        ArrayList<IncomeDto> incomes = new ArrayList<>();
        while (rs.next()) {
            incomes.add(new IncomeDto(rs.getString(1), rs.getDouble(2), rs.getDate(3)));
        }
        return incomes;
    }
    @Override
    public  Map<String, Double> getMonthlyIncomesTotal() throws SQLException, ClassNotFoundException {
        ResultSet rs  = SQLUtil.execute("SELECT * FROM Income");
        ArrayList<IncomeDto> incomes = new ArrayList<>();
        Map<String, Double> monthlyTotals = new HashMap<>();
        while (rs.next()) {
            String month = getMonthFromDate(rs.getDate(3));
            monthlyTotals.merge(month, rs.getDouble(2), Double::sum);
        }
        return monthlyTotals;
    }
    @Override
    public   String getMonthFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        return String.format("%02d", month);
    }
}
