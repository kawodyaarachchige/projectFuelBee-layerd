package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dao.CrudDAO;
import lk.ijse.fuelBee.dto.FuelDto;
import lk.ijse.fuelBee.entity.Fuel;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FuelDAO extends CrudDAO<Fuel> {
   // ArrayList<FuelTypeDto> getAllFuelType() throws SQLException, ClassNotFoundException;

   // boolean save(Fuel entity) throws SQLException, ClassNotFoundException;

    String getFuelIdByName(String name) throws SQLException, ClassNotFoundException;

    Double getFuelPriceByName(String name) throws SQLException, ClassNotFoundException;

    ArrayList<Double> getFuelPrices() throws SQLException, ClassNotFoundException;
}
