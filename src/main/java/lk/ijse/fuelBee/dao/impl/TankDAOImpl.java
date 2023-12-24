package lk.ijse.fuelBee.dao.impl;

import lk.ijse.fuelBee.dao.custom.TankDAO;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.TankDto;

import java.sql.*;
import java.util.ArrayList;

public class TankDAOImpl implements TankDAO {
    @Override
    public  boolean saveTank(TankDto tankDto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "INSERT INTO Tank VALUES(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, tankDto.getTankId());
        preparedStatement.setString(2, tankDto.getFuelType());
        preparedStatement.setInt(3, tankDto.getQty());
        preparedStatement.setInt(4, tankDto.getRemainingFuel());
        preparedStatement.setInt(5, tankDto.getCapacityOfWaste());
        preparedStatement.setDate(6, Date.valueOf(String.valueOf(tankDto.getDate())));

        if (preparedStatement.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }
   @Override
    public  boolean deleteTank(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "DELETE FROM Tank WHERE tank_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        if (pstm.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }
  @Override
    public  boolean updateTank(TankDto tankDto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "UPDATE Tank SET type=?,qty=?,remaining_fuel_in_tank=?,capacity_of_waste=?,date=? WHERE tank_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, tankDto.getFuelType());
        pstm.setInt(2, tankDto.getQty());
        pstm.setInt(3, tankDto.getRemainingFuel()-tankDto.getCapacityOfWaste());
        pstm.setInt(4, tankDto.getCapacityOfWaste());
        pstm.setDate(5, Date.valueOf(String.valueOf(tankDto.getDate())));
        pstm.setString(6, tankDto.getTankId());
        if (pstm.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public ArrayList<TankDto> getAllTank() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM Tank";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rst = pstm.executeQuery();

        ArrayList<TankDto> tanks = new ArrayList<>();
        while (rst.next()) {
            tanks.add(new TankDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4),
                    rst.getInt(5),
                    rst.getDate(6)
            ));
        }
        return tanks;
    }
   @Override
    public TankDto searchTank(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM Tank WHERE tank_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet rst = pstm.executeQuery();
        if(rst.next()){
            return new TankDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4),
                    rst.getInt(5),
                    rst.getDate(6)
            );
        }else{
            return null;
        }
    }
}
