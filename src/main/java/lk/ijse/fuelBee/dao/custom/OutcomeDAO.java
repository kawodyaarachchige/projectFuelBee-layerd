package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dto.OutcomeDto;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface OutcomeDAO {
    ArrayList<OutcomeDto> getAllOutcomes() throws SQLException;
    ArrayList<OutcomeDto> getAllOutcomesByDate(java.util.Date startDate, java.util.Date endDate) throws SQLException;

    Map<String, Double> getMonthlyOutcomesTotal() throws SQLException;

     String getMonthFromDate(Date date);
}
