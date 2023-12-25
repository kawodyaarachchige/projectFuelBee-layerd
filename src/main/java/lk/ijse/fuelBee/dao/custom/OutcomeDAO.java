package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dao.CrudDAO;
import lk.ijse.fuelBee.dto.OutcomeDto;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface OutcomeDAO extends CrudDAO<OutcomeDto> {
   // ArrayList<OutcomeDto> getAllOutcomes() throws SQLException, ClassNotFoundException;
    ArrayList<OutcomeDto> getAllOutcomesByDate(java.util.Date startDate, java.util.Date endDate) throws SQLException, ClassNotFoundException;

    Map<String, Double> getMonthlyOutcomesTotal() throws SQLException, ClassNotFoundException;

     String getMonthFromDate(Date date);
     boolean deleteOutcome(String id) throws SQLException;
}
