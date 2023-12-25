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
    public  ArrayList<IncomeDto> getAll() throws SQLException, ClassNotFoundException {
       ResultSet rs = SQLUtil.execute("SELECT * FROM Income");
       ArrayList<IncomeDto> incomes = new ArrayList<>();
        while (rs.next()) {
            incomes.add(new IncomeDto(rs.getString(1), rs.getDouble(2), rs.getDate(3)));
        }
        return incomes;
    }

    @Override
    public boolean save(IncomeDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Income VALUES(?,?,?)",dto.getIncomeId(),dto.getAmount(),dto.getDate());
    }

    @Override
    public boolean update(IncomeDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public IncomeDto search(String id) throws SQLException {
        return null;
    }

    @Override
    public IncomeDto get(String id) throws SQLException {
        return null;
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
    @Override
    public boolean deleteIncome(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            boolean isDeleted = SQLUtil.execute("DELETE FROM Machine WHERE machine_id=?",id);
            boolean isDeleted1 =SQLUtil.execute("DELETE FROM Income WHERE income_id=?",id);
            if ((isDeleted ) && (isDeleted1 )) {
                connection.commit();
                return true;
            }else{
                connection.rollback();
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
