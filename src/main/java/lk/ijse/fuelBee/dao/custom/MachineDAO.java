package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dto.MachineDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MachineDAO {
    ArrayList<MachineDto> getAllMachines() throws SQLException;
    boolean changeDayEndFuelByWaste(String id,int waste) throws SQLException;
    boolean checkDayEndAmounts(String id,int amount) throws SQLException;
    ArrayList<MachineDto> getCapacityLowFuels() throws SQLException;

    MachineDto searchMachine(String id) throws SQLException;


}
