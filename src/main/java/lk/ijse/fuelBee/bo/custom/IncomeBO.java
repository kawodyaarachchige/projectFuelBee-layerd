package lk.ijse.fuelBee.bo.custom;

import lk.ijse.fuelBee.bo.SuperBO;
import lk.ijse.fuelBee.dto.IncomeDto;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface IncomeBO extends SuperBO {
   // boolean save(Income entity) throws SQLException, ClassNotFoundException;

    ArrayList<IncomeDto> getAllIncome() throws SQLException, ClassNotFoundException;

    boolean saveIncome(IncomeDto dto) throws SQLException, ClassNotFoundException;

    boolean updateIncome(IncomeDto dto) throws SQLException, ClassNotFoundException;

    // ArrayList<IncomeDto> getAllIncomes() throws SQLException, ClassNotFoundException;
    ArrayList<IncomeDto> getAllIncomesByDate(java.util.Date startDate, java.util.Date endDate) throws SQLException, ClassNotFoundException;
    Map<String, Double> getMonthlyIncomesTotal() throws SQLException, ClassNotFoundException;
    String getMonthFromDate(Date date);

    boolean deleteIncome(String id) throws SQLException;

    // boolean deleteIncome(String id) throws SQLException;

}
