package lk.ijse.fuelBee.bo.custom;

import lk.ijse.fuelBee.bo.SuperBO;
import lk.ijse.fuelBee.dto.MachineDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MachineBO extends SuperBO {

    ArrayList<MachineDto> getAllMachine() throws SQLException, ClassNotFoundException;

    boolean saveMachine(MachineDto dto) throws SQLException, ClassNotFoundException;

    boolean updateMachine(MachineDto dto) throws SQLException, ClassNotFoundException;

    boolean changeDayEndFuelByWaste(String id,int waste) throws SQLException, ClassNotFoundException;
    boolean checkDayEndAmounts(String id,int amount) throws SQLException, ClassNotFoundException;
    ArrayList<MachineDto> getCapacityLowFuels() throws SQLException, ClassNotFoundException;

   boolean confirmMachineUsage(MachineDto dto) throws SQLException;

    Double getFuelPriceByName(String name) throws SQLException, ClassNotFoundException;
    boolean updateMachineSpecs(MachineDto dto) throws SQLException;

    MachineDto searchMachine(String id) throws SQLException, ClassNotFoundException;
    boolean deleteMachine(String id) throws SQLException, ClassNotFoundException;



}
