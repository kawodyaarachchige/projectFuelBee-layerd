package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dao.CrudDAO;
import lk.ijse.fuelBee.dto.IncomeDto;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface IncomeDAO extends CrudDAO<IncomeDto> {
   // ArrayList<IncomeDto> getAllIncomes() throws SQLException, ClassNotFoundException;
    ArrayList<IncomeDto> getAllIncomesByDate(java.util.Date startDate, java.util.Date endDate) throws SQLException, ClassNotFoundException;
    Map<String, Double> getMonthlyIncomesTotal() throws SQLException, ClassNotFoundException;
    String getMonthFromDate(Date date);

    boolean deleteIncome(String id) throws SQLException;

}
