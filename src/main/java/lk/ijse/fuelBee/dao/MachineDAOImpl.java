package lk.ijse.fuelBee.dao;

import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.MachineDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MachineDAOImpl {
    public ArrayList<MachineDto> getAllMachines() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM Machine";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rst = pstm.executeQuery();

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
    public boolean changeDayEndFuelByWaste(String id,int waste) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql="UPDATE Machine SET day_end_fuel_amount=day_end_fuel_amount-? WHERE fuel_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1,waste);
        pstm.setString(2,id);

        if(pstm.executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }
    public boolean checkDayEndAmounts(String id,int amount) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM Machine WHERE fuel_id=? AND day_end_fuel_amount = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        pstm.setInt(2, amount);

        ResultSet rst = pstm.executeQuery();
        while (rst.next()) {
            return true;
        }
        return false;
    }
    public ArrayList<MachineDto> getCapacityLowFuels() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql="SELECT * FROM Machine WHERE day_end_fuel_amount < 1000";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rst = pstm.executeQuery();
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
    public MachineDto searchMachine(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM Machine WHERE machine_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet rst = pstm.executeQuery();
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



