package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dto.MachineDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MachineDAO {
    ArrayList<MachineDto> getAllMachines() throws SQLException, ClassNotFoundException;
    boolean changeDayEndFuelByWaste(String id,int waste) throws SQLException, ClassNotFoundException;
    boolean checkDayEndAmounts(String id,int amount) throws SQLException, ClassNotFoundException;
    ArrayList<MachineDto> getCapacityLowFuels() throws SQLException, ClassNotFoundException;

    MachineDto searchMachine(String id) throws SQLException, ClassNotFoundException;


}
