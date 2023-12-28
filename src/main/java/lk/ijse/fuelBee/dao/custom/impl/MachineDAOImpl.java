package lk.ijse.fuelBee.dao.custom.impl;

import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.MachineDAO;
import lk.ijse.fuelBee.dto.EmployeeDto;
import lk.ijse.fuelBee.dto.MachineDto;
import lk.ijse.fuelBee.entity.Machine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MachineDAOImpl implements MachineDAO {
  //  IncomeDAO incomeDAO = new IncomeDAOImpl();
   // MachineDAO machineDAO = new MachineDAOImpl();
    @Override
    public ArrayList<Machine> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Machine");
        ArrayList<Machine> machines = new ArrayList<>();
        while(rst.next()){
            machines.add(new Machine(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getInt(6),
                    rst.getDate(7)
            ));
        }return machines;
    }

    @Override
    public boolean save(Machine entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Machine VALUES(?,?,?,?,?,?,?)",entity.getMachineId(),entity.getFuelId(),entity.getFuelType(),entity.getAvailability(),entity.getStartFuelAmount(),entity.getEndFuelAmount(),entity.getDate());
    }

    @Override
    public boolean update(Machine entity) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute( "UPDATE Machine SET machine_id=?,fuel_id=?,type=?,availability=?,start_fuel_amount=?,day_end_fuel_amount=?,date=? WHERE machine_id=?",entity.getMachineId(),entity.getFuelId(),entity.getFuelType(),entity.getAvailability(),entity.getStartFuelAmount(),entity.getEndFuelAmount(),entity.getDate(),entity.getMachineId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Machine WHERE machine_id=?", id);
    }

    @Override
    public Machine search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Machine WHERE machine_id=?", id);

        if(resultSet.next()){
            return new Machine(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getInt(6),
                    resultSet.getDate(7)
            );
        }else {
            return null;
        }
    }

    @Override
    public Machine get(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean changeDayEndFuelByWaste(String id,int waste) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute("UPDATE Machine SET day_end_fuel_amount=day_end_fuel_amount-? WHERE fuel_id=?", waste, id);

    }
    @Override
    public boolean checkDayEndAmounts(String id,int amount) throws SQLException, ClassNotFoundException {
        ResultSet rst= SQLUtil.execute("SELECT * FROM Machine WHERE fuel_id=? AND day_end_fuel_amount = ?", id, amount);
        while (rst.next()) {
            return true;
        }
        return false;
    }
    @Override
    public ArrayList<Machine> getCapacityLowFuels() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Machine WHERE day_end_fuel_amount < 1000");
        ArrayList<Machine> lowFuelMachines = new ArrayList<>();
        while (rst.next()){
            lowFuelMachines.add(new Machine(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getInt(6),
                    rst.getDate(7)
            ));
        }
        return lowFuelMachines;
    }

    /*@Override
    public  boolean confirmMachineUsage(MachineDto machineDto) throws SQLException {

        Connection connection = Dbconnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            // boolean isSaved = SQLUtil.execute("INSERT INTO Machine VALUES(?,?,?,?,?,?,?)",machineDto.getMachineId(),machineDto.getFuelId(),machineDto.getFuelType(),machineDto.getAvailability(),machineDto.getStartFuelAmount(),machineDto.getEndFuelAmount(),machineDto.getDate());
            boolean isSaved=machineDAO.save(machineDto);
            Double fuelPrice =getFuelPriceByName(machineDto.getFuelType());
            Double soldFuelAmount= (double) ((machineDto.getStartFuelAmount())-(machineDto.getEndFuelAmount()));
            IncomeDto incomeDTO = new IncomeDto(machineDto.getMachineId(),soldFuelAmount,machineDto.getDate());
            boolean isIncomeSaved = incomeDAO.save(incomeDTO);
            // boolean isIncomeSaved = SQLUtil.execute("INSERT INTO Income VALUES(?,?,?)",machineDto.getMachineId(),machineDto.getFuelType(),machineDto.getDate());
            System.out.println("sold Fuel Amount is : "+soldFuelAmount+"L and fuel price is : Rs."+fuelPrice);

            if ((isSaved ) && (isIncomeSaved )) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                System.out.println("Failed to Complete the Machine Usage save & Income save");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }*/

    @Override
    public Double getFuelPriceByName(String name) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT price FROM Fuel WHERE type=?", name);
        if(rst.next()){
            return rst.getDouble(1);
        }else{
            return null;
        }
    }
   /* @Override
    public  boolean updateMachineSpecs(MachineDto machineDto) throws SQLException {
        MachineDAO machineDAO = new MachineDAOImpl();
        Connection connection = Dbconnection.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            boolean isUpdated = machineDAO.update(machineDto);
           // boolean isUpdated = SQLUtil.execute("UPDATE Machine SET machine_id=?,fuel_id=?,type=?,availability=?,start_fuel_amount=?,day_end_fuel_amount=?,date=? WHERE machine_id=?",machineDto.getMachineId(),machineDto.getFuelType(),machineDto.getDate(),machineDto.getMachineId());
            Double fuelPrice =machineDAO.getFuelPriceByName(machineDto.getFuelType())*(machineDto.getStartFuelAmount()-machineDto.getEndFuelAmount());
            IncomeDto incomeDTO = new IncomeDto(machineDto.getMachineId(),fuelPrice,machineDto.getDate());
            boolean isIncomeUpdated = incomeDAO.update(incomeDTO);
            // boolean isIncomeUpdated = SQLUtil.execute("UPDATE Income SET income_id=?,amount=?,date=? WHERE income_id=?",machineDto.getMachineId(),fuelPrice,machineDto.getDate());
            if((isUpdated ) && (isIncomeUpdated )) {
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
    }*/


}