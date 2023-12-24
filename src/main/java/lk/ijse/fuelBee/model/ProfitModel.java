package lk.ijse.fuelBee.model;

import lk.ijse.fuelBee.dao.IncomeDAOImpl;
import lk.ijse.fuelBee.dao.OutcomeDAOImpl;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.IncomeDto;
import lk.ijse.fuelBee.dto.OutcomeDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ProfitModel {
    public static ArrayList<OutcomeDto> getAllOutcomes() throws SQLException {
        OutcomeDAOImpl outcomeDAO = new OutcomeDAOImpl();
        ArrayList<OutcomeDto> allOutcomes = outcomeDAO.getAllOutcomes();
        if(allOutcomes != null){
            return allOutcomes;
        }else{
            return null;
        }
    }

    public static ArrayList<IncomeDto> getAllIncomes() throws SQLException {
        IncomeDAOImpl incomeDAO = new IncomeDAOImpl();
        ArrayList<IncomeDto> allIncomes = incomeDAO.getAllIncomes();
        if(allIncomes != null){
            return allIncomes;
        }else{
            return null;
        }

    }

    public static ArrayList<OutcomeDto> getAllOutcomesByDate(java.util.Date startDate, java.util.Date endDate) throws SQLException {
        OutcomeDAOImpl outcomeDAO = new OutcomeDAOImpl();
        ArrayList<OutcomeDto> allOutcomesByDate = outcomeDAO.getAllOutcomesByDate(startDate, endDate);
        if(allOutcomesByDate != null){
            return allOutcomesByDate;
        }else{
            return null;
        }
    }
    public static ArrayList<IncomeDto> getAllIncomesByDate(java.util.Date startDate, java.util.Date endDate) throws SQLException {

        IncomeDAOImpl incomeDAO = new IncomeDAOImpl();
        ArrayList<IncomeDto> allIncomesByDate = incomeDAO.getAllIncomesByDate(startDate, endDate);
        if (allIncomesByDate != null) {
            return allIncomesByDate;
        } else {
            return null;
        }
    }
    public static Map<String, Double> getMonthlyOutcomesTotal() throws SQLException {
        OutcomeDAOImpl outcomeDAO = new OutcomeDAOImpl();
        Map<String, Double> monthlyOutcomesTotal = outcomeDAO.getMonthlyOutcomesTotal();
        if(monthlyOutcomesTotal != null){
            return monthlyOutcomesTotal;
        }else{
            return null;
        }
    }

    public static Map<String, Double> getMonthlyIncomesTotal() throws SQLException {
        IncomeDAOImpl incomeDAO = new IncomeDAOImpl();
        Map<String, Double> monthlyIncomesTotal = incomeDAO.getMonthlyIncomesTotal();
        if(monthlyIncomesTotal != null){
            return monthlyIncomesTotal;
        }else{
            return null;
        }
    }
}
