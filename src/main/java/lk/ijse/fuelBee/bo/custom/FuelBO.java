package lk.ijse.fuelBee.bo.custom;

import lk.ijse.fuelBee.bo.SuperBO;
import lk.ijse.fuelBee.dto.FuelDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FuelBO extends SuperBO {
   // ArrayList<FuelTypeDto> getAllFuelType() throws SQLException, ClassNotFoundException;

   // boolean save(FuelDto dto) throws SQLException, ClassNotFoundException;

    ArrayList<FuelDto> getAllFuel() throws SQLException, ClassNotFoundException;

    String getFuelIdByName(String name) throws SQLException, ClassNotFoundException;

    Double getFuelPriceByName(String name) throws SQLException, ClassNotFoundException;

    ArrayList<Double> getFuelPrices() throws SQLException, ClassNotFoundException;
}
