package lk.ijse.fuelBee.dao.custom.impl;

import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.FuelDAO;
import lk.ijse.fuelBee.dto.EmployeeDto;
import lk.ijse.fuelBee.dto.MachineDto;
import lk.ijse.fuelBee.entity.Fuel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuelDAOImpl implements FuelDAO {
   @Override
    public ArrayList<Fuel> getAll() throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtil.execute("SELECT * FROM Fuel");
        ArrayList<Fuel> fuelTypes = new ArrayList<>();
        while (rst.next()) {
            fuelTypes.add(new Fuel(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)
            ));
        }
        return fuelTypes;
    }

    @Override
    public boolean save(Fuel entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Fuel entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Fuel search(String id) throws SQLException {
        return null;
    }

    @Override
    public Fuel get(String id) throws SQLException {
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


