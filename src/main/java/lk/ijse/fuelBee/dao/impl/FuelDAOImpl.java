package lk.ijse.fuelBee.dao.impl;

import lk.ijse.fuelBee.dao.SQLUtil;
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
    public ArrayList<FuelTypeDto> getAll() throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtil.execute("SELECT * FROM Fuel");
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
    public boolean save(FuelTypeDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(FuelTypeDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public FuelTypeDto search(String id) throws SQLException {
        return null;
    }

    @Override
    public FuelTypeDto get(String id) throws SQLException {
        return null;
    }



    @Override
    public String getFuelIdByName(String name) throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtil.execute("SELECT fuel_id FROM Fuel WHERE type=?", name);
        if(rst.next()){
            return rst.getString(1);
        }else{
            return null;
        }

    }

    @Override
    public Double getFuelPriceByName(String name) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT price FROM Fuel WHERE type=?", name);
        if(rst.next()){
            return rst.getDouble(1);
        }else{
            return null;
        }
    }

    @Override
    public ArrayList<Double> getFuelPrices() throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtil.execute("SELECT price FROM Fuel");
       ArrayList<Double> prices = new ArrayList<>();
       while (rst.next()) {
           prices.add(rst.getDouble(1));
       }
       return prices;
   }


}


