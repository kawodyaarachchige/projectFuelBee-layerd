package lk.ijse.fuelBee.bo.custom.impl;

import lk.ijse.fuelBee.bo.custom.FuelBO;
import lk.ijse.fuelBee.dao.DAOFactory;
import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.FuelDAO;
import lk.ijse.fuelBee.dto.FuelDto;
import lk.ijse.fuelBee.entity.Fuel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuelBOImpl implements FuelBO {
    FuelDAO fuelDAO = (FuelDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.FUEL);
   @Override
    public ArrayList<FuelDto> getAllFuel() throws SQLException, ClassNotFoundException {
       ArrayList<Fuel> fuelTypes = fuelDAO.getAll();
       ArrayList<FuelDto> fuelDtos = new ArrayList<>();
       for(Fuel fuel : fuelTypes){
           fuelDtos.add(new FuelDto(
                   fuel.getFuelId(),
                   fuel.getFuelType(),
                   fuel.getQty(),
                   fuel.getPrice()
           ));
       }
       return fuelDtos;
       /*ResultSet rst = SQLUtil.execute("SELECT * FROM Fuel");
        ArrayList<FuelDto> fuelTypes = new ArrayList<>();
        while (rst.next()) {
            fuelTypes.add(new FuelDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)
            ));
        }
        return fuelTypes;*/
    }


    @Override
    public String getFuelIdByName(String name) throws SQLException, ClassNotFoundException {
       return fuelDAO.getFuelIdByName(name);
       /*ResultSet rst = SQLUtil.execute("SELECT fuel_id FROM Fuel WHERE type=?", name);
        if(rst.next()){
            return rst.getString(1);
        }else{
            return null;
        }*/

    }

    @Override
    public Double getFuelPriceByName(String name) throws SQLException, ClassNotFoundException {
       return fuelDAO.getFuelPriceByName(name);
        /*ResultSet rst = SQLUtil.execute("SELECT price FROM Fuel WHERE type=?", name);
        if(rst.next()){
            return rst.getDouble(1);
        }else{
            return null;
        }*/
    }

    @Override
    public ArrayList<Double> getFuelPrices() throws SQLException, ClassNotFoundException {
       ArrayList<Double> prices = fuelDAO.getFuelPrices();
       ArrayList<Double> newPrices = new ArrayList<>();
       for (Double price : prices) {
           newPrices.add(price);
       }
       return newPrices;
      /* ResultSet rst = SQLUtil.execute("SELECT price FROM Fuel");
       ArrayList<Double> prices = new ArrayList<>();
       while (rst.next()) {
           prices.add(rst.getDouble(1));
       }
       return prices;*/
   }

}


