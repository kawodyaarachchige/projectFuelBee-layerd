package lk.ijse.fuelBee.bo.custom.impl;

import lk.ijse.fuelBee.bo.custom.IncomeBO;
import lk.ijse.fuelBee.dao.DAOFactory;
import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.IncomeDAO;
import lk.ijse.fuelBee.dao.custom.MachineDAO;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.IncomeDto;
import lk.ijse.fuelBee.entity.Income;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

;

public class IncomeBOImpl implements IncomeBO {
    IncomeDAO incomeDAO = (IncomeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.INCOME);
    MachineDAO machineDAO = (MachineDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.MACHINE);
   @Override
    public ArrayList<IncomeDto> getAllIncome() throws SQLException, ClassNotFoundException {
       ArrayList<Income> incomes = incomeDAO.getAll();
       ArrayList<IncomeDto> incomeDtos = new ArrayList<>();
       for (Income income : incomes) {
           incomeDtos.add(new IncomeDto(
                   income.getIncomeId(),
                   income.getAmount(),
                   income.getDate()
           ));
       }
       return incomeDtos;
      /* ResultSet rs = SQLUtil.execute("SELECT * FROM Income");
       ArrayList<IncomeDto> incomes = new ArrayList<>();
        while (rs.next()) {
            incomes.add(new IncomeDto(rs.getString(1), rs.getDouble(2), rs.getDate(3)));
        }
        return incomes;*/
    }

    @Override
    public boolean saveIncome(IncomeDto dto) throws SQLException, ClassNotFoundException {
       return incomeDAO.save(new Income(dto.getIncomeId(),dto.getAmount(),dto.getDate()));
        //return SQLUtil.execute("INSERT INTO Income VALUES(?,?,?)",dto.getIncomeId(),dto.getAmount(),dto.getDate());
    }

    @Override
    public boolean updateIncome(IncomeDto dto) throws SQLException, ClassNotFoundException {
       return incomeDAO.update(new Income(dto.getIncomeId(),dto.getAmount(),dto.getDate()));
        //return SQLUtil.execute("UPDATE Income SET income_id=?,amount=?,date=? WHERE income_id=?", dto.getIncomeId(), dto.getAmount(), dto.getDate());
    }

    @Override
    public ArrayList<IncomeDto> getAllIncomesByDate(java.util.Date startDate, java.util.Date endDate) throws SQLException, ClassNotFoundException {
       ArrayList<Income> incomes = incomeDAO.getAllIncomesByDate(startDate, endDate);
       ArrayList<IncomeDto> incomeDtos = new ArrayList<>();
       for (Income income : incomes) {
           incomeDtos.add(new IncomeDto(
                   income.getIncomeId(),
                   income.getAmount(),
                   income.getDate()
           ));
       }
       return incomeDtos;
        /*ResultSet rs = SQLUtil.execute("SELECT * FROM Income WHERE date BETWEEN ? AND ?", startDate, endDate);
        ArrayList<IncomeDto> incomes = new ArrayList<>();
        while (rs.next()) {
            incomes.add(new IncomeDto(rs.getString(1), rs.getDouble(2), rs.getDate(3)));
        }
        return incomes;*/
    }
    @Override
    public  Map<String, Double> getMonthlyIncomesTotal() throws SQLException, ClassNotFoundException {
       ArrayList<Income> incomes = incomeDAO.getAll();
       ArrayList<IncomeDto> incomeDtos = new ArrayList<>();
       for (Income income : incomes) {
           incomeDtos.add(new IncomeDto(
                   income.getIncomeId(),
                   income.getAmount(),
                   income.getDate()
           ));
       }
       Map<String, Double> monthlyTotals = new HashMap<>();
       for (IncomeDto incomeDto : incomeDtos) {
           String month = getMonthFromDate(incomeDto.getDate());
           monthlyTotals.merge(month, incomeDto.getAmount(), Double::sum);
       }
       return monthlyTotals;
      /*  ResultSet rs  = SQLUtil.execute("SELECT * FROM Income");
       ArrayList<IncomeDto> incomes = new ArrayList<>();
        Map<String, Double> monthlyTotals = new HashMap<>();
        while (rs.next()) {
            String month = getMonthFromDate(rs.getDate(3));
            monthlyTotals.merge(month, rs.getDouble(2), Double::sum);
        }
        return monthlyTotals;*/
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
            boolean isDeleted = machineDAO.delete(id);
           // boolean isDeleted = SQLUtil.execute("DELETE FROM Machine WHERE machine_id=?",id);
           boolean isDeleted1 = incomeDAO.delete(id);
           // boolean isDeleted1 =SQLUtil.execute("DELETE FROM Income WHERE income_id=?",id);
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
