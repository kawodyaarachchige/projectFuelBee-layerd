package lk.ijse.fuelBee.bo.custom.impl;

import lk.ijse.fuelBee.bo.custom.MachineBO;
import lk.ijse.fuelBee.dao.DAOFactory;
import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.IncomeDAO;
import lk.ijse.fuelBee.dao.custom.MachineDAO;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.IncomeDto;
import lk.ijse.fuelBee.dto.MachineDto;
import lk.ijse.fuelBee.entity.Income;
import lk.ijse.fuelBee.entity.Machine;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MachineBOImpl implements MachineBO {
   MachineDAO machineDAO = (MachineDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.MACHINE);
   IncomeDAO incomeDAO = (IncomeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.INCOME);
    @Override
    public ArrayList<MachineDto> getAllMachine() throws SQLException, ClassNotFoundException {
        ArrayList<Machine> machines = machineDAO.getAll();
        ArrayList<MachineDto> machineDTOS = new ArrayList<>();
        for (Machine machine : machines) {
            machineDTOS.add(new MachineDto(machine.getMachineId(), machine.getFuelId(), machine.getFuelType(), machine.getAvailability(), machine.getStartFuelAmount(), machine.getEndFuelAmount(), machine.getDate()));
        }return machineDTOS;
    }

    @Override
    public boolean saveMachine(MachineDto dto) throws SQLException, ClassNotFoundException {
        return  machineDAO.save(new Machine(dto.getMachineId(), dto.getFuelId(), dto.getFuelType(), dto.getAvailability(), dto.getStartFuelAmount(), dto.getEndFuelAmount(), dto.getDate()));
      //  return SQLUtil.execute("INSERT INTO Machine VALUES(?,?,?,?,?,?,?)",dto.getMachineId(),dto.getFuelId(),dto.getFuelType(),dto.getAvailability(),dto.getStartFuelAmount(),dto.getEndFuelAmount(),dto.getDate());
   // return customerDAO.save(new Customer(customer.getId(), customer.getName(), customer.getAddress()));
    }

    @Override
    public boolean updateMachine(MachineDto dto) throws SQLException, ClassNotFoundException {
      return  machineDAO.update(new Machine(dto.getMachineId(), dto.getFuelId(), dto.getFuelType(), dto.getAvailability(), dto.getStartFuelAmount(), dto.getEndFuelAmount(), dto.getDate()));
       //return SQLUtil.execute( "UPDATE Machine SET machine_id=?,fuel_id=?,type=?,availability=?,start_fuel_amount=?,day_end_fuel_amount=?,date=? WHERE machine_id=?",dto.getMachineId(),dto.getFuelId(),dto.getFuelType(),dto.getAvailability(),dto.getStartFuelAmount(),dto.getEndFuelAmount(),dto.getDate(),dto.getMachineId());
    }


    @Override
    public boolean changeDayEndFuelByWaste(String id,int waste) throws SQLException, ClassNotFoundException {
       // return  SQLUtil.execute("UPDATE Machine SET day_end_fuel_amount=day_end_fuel_amount-? WHERE fuel_id=?", waste, id);
        return  machineDAO.changeDayEndFuelByWaste(id, waste);
    }
    @Override
    public boolean checkDayEndAmounts(String id,int amount) throws SQLException, ClassNotFoundException {
        return  machineDAO.checkDayEndAmounts(id, amount);
        /*
        ResultSet rst= SQLUtil.execute("SELECT * FROM Machine WHERE fuel_id=? AND day_end_fuel_amount = ?", id, amount);
        while (rst.next()) {
            return true;
        }
        return false;*/
    }
    @Override
    public ArrayList<MachineDto> getCapacityLowFuels() throws SQLException, ClassNotFoundException {
        ArrayList<Machine> machines = machineDAO.getCapacityLowFuels();
        ArrayList<MachineDto> machineDTOS = new ArrayList<>();
        for (Machine machine : machines) {
            machineDTOS.add(new MachineDto(machine.getMachineId(), machine.getFuelId(), machine.getFuelType(), machine.getAvailability(), machine.getStartFuelAmount(), machine.getEndFuelAmount(), machine.getDate()));
        }return machineDTOS;
       /* ResultSet rst = SQLUtil.execute("SELECT * FROM Machine WHERE day_end_fuel_amount < 1000");
        ArrayList<MachineDto> lowFuelMachines = new ArrayList<>();
        while (rst.next()){
            lowFuelMachines.add(new MachineDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getInt(6),
                    rst.getDate(7)
            ));
        }
        return lowFuelMachines;*/
    }

    @Override
    public  boolean confirmMachineUsage(MachineDto dto) throws SQLException {

        Connection connection = Dbconnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            // boolean isSaved = SQLUtil.execute("INSERT INTO Machine VALUES(?,?,?,?,?,?,?)",machineDto.getMachineId(),machineDto.getFuelId(),machineDto.getFuelType(),machineDto.getAvailability(),machineDto.getStartFuelAmount(),machineDto.getEndFuelAmount(),machineDto.getDate());
            boolean isSaved=machineDAO.save(new Machine(dto.getMachineId(), dto.getFuelId(), dto.getFuelType(), dto.getAvailability(), dto.getStartFuelAmount(), dto.getEndFuelAmount(), dto.getDate()));
            Double fuelPrice =getFuelPriceByName(dto.getFuelType());
            Double soldFuelAmount= (double) ((dto.getStartFuelAmount())-(dto.getEndFuelAmount()));
            //IncomeDto incomeDto = new IncomeDto(machineDto.getMachineId(),soldFuelAmount,machineDto.getDate());
            boolean isIncomeSaved = incomeDAO.save(new Income(dto.getMachineId(),soldFuelAmount,dto.getDate()));
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
    }

    @Override
    public Double getFuelPriceByName(String name) throws SQLException, ClassNotFoundException {
        return machineDAO.getFuelPriceByName(name);
/*
        ResultSet rst = SQLUtil.execute("SELECT price FROM Fuel WHERE type=?", name);
        if(rst.next()){
            return rst.getDouble(1);
        }else{
            return null;
        }*/
    }
    @Override
    public  boolean updateMachineSpecs(MachineDto dto) throws SQLException {

        Connection connection = Dbconnection.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            boolean isUpdated = machineDAO.update(new Machine(dto.getMachineId(), dto.getFuelId(), dto.getFuelType(), dto.getAvailability(), dto.getStartFuelAmount(), dto.getEndFuelAmount(), dto.getDate()));
           // boolean isUpdated = SQLUtil.execute("UPDATE Machine SET machine_id=?,fuel_id=?,type=?,availability=?,start_fuel_amount=?,day_end_fuel_amount=?,date=? WHERE machine_id=?",machineDto.getMachineId(),machineDto.getFuelType(),machineDto.getDate(),machineDto.getMachineId());
            Double fuelPrice =machineDAO.getFuelPriceByName(dto.getFuelType())*(dto.getStartFuelAmount()-dto.getEndFuelAmount());
           // IncomeDto incomeDto = new IncomeDto(dto.getMachineId(),fuelPrice,dto.getDate());
            boolean isIncomeUpdated = incomeDAO.update(new Income(dto.getMachineId(),fuelPrice,dto.getDate()));
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
    }

    @Override
    /*public MachineDto searchMachine(String id) throws SQLException, ClassNotFoundException {
        return machineDAO.search(id);
    }*/
    public MachineDto searchMachine(String id) throws SQLException, ClassNotFoundException {
       // return new MachineDto(search.getMachineId(),search.getFuelId(),search.getFuelType(),search.getAvailability(),search.getStartFuelAmount(),search.getEndFuelAmount(),search.getDate());
         Machine search = machineDAO.search(id);
         return new MachineDto(search.getMachineId(),search.getFuelId(),search.getFuelType(),search.getAvailability(),search.getStartFuelAmount(),search.getEndFuelAmount(),search.getDate());


    }

}