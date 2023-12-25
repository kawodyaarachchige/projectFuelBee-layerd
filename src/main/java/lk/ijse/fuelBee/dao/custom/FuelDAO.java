package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dto.FuelTypeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FuelDAO {
    ArrayList<FuelTypeDto> getAllFuelType() throws SQLException, ClassNotFoundException;

    String getFuelIdByName(String name) throws SQLException, ClassNotFoundException;

    Double getFuelPriceByName(String name) throws SQLException, ClassNotFoundException;

    ArrayList<Double> getFuelPrices() throws SQLException, ClassNotFoundException;
}
