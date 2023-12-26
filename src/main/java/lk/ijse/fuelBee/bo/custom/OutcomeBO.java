package lk.ijse.fuelBee.bo.custom;

import lk.ijse.fuelBee.bo.SuperBO;
import lk.ijse.fuelBee.dao.CrudDAO;
import lk.ijse.fuelBee.dto.OutcomeDto;
import lk.ijse.fuelBee.entity.Outcome;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface OutcomeBO extends SuperBO {
    boolean saveOutcome(OutcomeDto dto) throws SQLException, ClassNotFoundException;

    boolean updateOutcome(OutcomeDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteOutcomes(String id) throws SQLException, ClassNotFoundException;

     ArrayList<OutcomeDto> getAllOutcomes() throws SQLException, ClassNotFoundException;

    boolean deleteOutcome(String id) throws SQLException, ClassNotFoundException;

    ArrayList<OutcomeDto> getAllOutcomesByDate(java.util.Date startDate, java.util.Date endDate) throws SQLException, ClassNotFoundException;

    Map<String, Double> getMonthlyOutcomesTotal() throws SQLException, ClassNotFoundException;

     String getMonthFromDate(Date date);


    // boolean deleteOutcome(String id) throws SQLException;
}
