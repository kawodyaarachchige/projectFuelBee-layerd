package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dto.IncomeDto;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface IncomeDAO {
    ArrayList<IncomeDto> getAllIncomes() throws SQLException;
    ArrayList<IncomeDto> getAllIncomesByDate(java.util.Date startDate, java.util.Date endDate) throws SQLException;
    Map<String, Double> getMonthlyIncomesTotal() throws SQLException;
    String getMonthFromDate(Date date);



}
