package lk.ijse.fuelBee.dao.impl;

import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.MachineDAO;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.MachineDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MachineDAOImpl implements MachineDAO {
   @Override
    public ArrayList<MachineDto> getAllMachines() throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtil.execute("SELECT * FROM Machine");
       ArrayList<MachineDto> machines = new ArrayList<>();
        while(rst.next()){
            machines.add(new MachineDto(
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
    public ArrayList<MachineDto> getCapacityLowFuels() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Machine WHERE day_end_fuel_amount < 1000");
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
        return lowFuelMachines;
    }
   @Override
    public MachineDto searchMachine(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Machine WHERE machine_id=?", id);
        if(rst.next()){
            return new MachineDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getInt(6),
                    rst.getDate(7)
            );
        }else{
            return null;
        }

    }
}



