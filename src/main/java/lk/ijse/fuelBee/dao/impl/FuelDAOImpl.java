package lk.ijse.fuelBee.dao.impl;

import lk.ijse.fuelBee.dao.custom.FuelDAO;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.FuelTypeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuelDAOImpl implements FuelDAO {
   @Override
    public ArrayList<FuelTypeDto> getAllFuelType() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM Fuel";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rst = pstm.executeQuery();

        ArrayList<FuelTypeDto> fuelTypes = new ArrayList<>();
        while (rst.next()) {
            fuelTypes.add(new FuelTypeDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)
            ));
        }
        return fuelTypes;

    }
   @Override
    public String getFuelIdByName(String name) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT fuel_id FROM Fuel WHERE type=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,name);

        ResultSet rst = pstm.executeQuery();
        if(rst.next()){
            return rst.getString(1);
        }else{
            return null;
        }

    }
    @Override
    public Double getFuelPriceByName(String name) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT price FROM Fuel WHERE type=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,name);

        ResultSet rst = pstm.executeQuery();
        if(rst.next()){
            return rst.getDouble(1);
        }else{
            return null;
        }
    }
   @Override
    public ArrayList<Double> getFuelPrices() throws SQLException {
       Connection connection = Dbconnection.getInstance().getConnection();
       String sql = "SELECT price FROM Fuel";
       PreparedStatement pstm = connection.prepareStatement(sql);
       ResultSet rst = pstm.executeQuery();
       ArrayList<Double> prices = new ArrayList<>();
       while (rst.next()) {
           prices.add(rst.getDouble(1));
       }
       return prices;
   }


}


