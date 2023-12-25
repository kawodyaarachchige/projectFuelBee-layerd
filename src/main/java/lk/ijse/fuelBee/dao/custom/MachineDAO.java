package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dao.CrudDAO;
import lk.ijse.fuelBee.dto.IncomeDto;
import lk.ijse.fuelBee.dto.MachineDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MachineDAO extends CrudDAO<MachineDto> {
   // ArrayList<MachineDto> getAllMachines() throws SQLException, ClassNotFoundException;
    boolean changeDayEndFuelByWaste(String id,int waste) throws SQLException, ClassNotFoundException;
    boolean checkDayEndAmounts(String id,int amount) throws SQLException, ClassNotFoundException;
    ArrayList<MachineDto> getCapacityLowFuels() throws SQLException, ClassNotFoundException;

   // MachineDto searchMachine(String id) throws SQLException, ClassNotFoundException;
   boolean confirmMachineUsage(MachineDto machineDto) throws SQLException;

    Double getFuelPriceByName(String name) throws SQLException, ClassNotFoundException;
    boolean updateMachineSpecs(MachineDto machineDto) throws SQLException;

}
