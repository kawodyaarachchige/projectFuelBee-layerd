package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dao.CrudDAO;
import lk.ijse.fuelBee.entity.Income;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface IncomeDAO extends CrudDAO<Income> {
   // boolean save(Income entity) throws SQLException, ClassNotFoundException;

     ArrayList<Income> getAll() throws SQLException, ClassNotFoundException;
    ArrayList<Income> getAllIncomesByDate(java.util.Date startDate, java.util.Date endDate) throws SQLException, ClassNotFoundException;
    Map<String, Double> getMonthlyIncomesTotal() throws SQLException, ClassNotFoundException;
    String getMonthFromDate(Date date);

  //  boolean deleteIncome(String id) throws SQLException;

}
