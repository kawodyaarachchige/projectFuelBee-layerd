package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dto.FuelTypeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FuelDAO {
    ArrayList<FuelTypeDto> getAllFuelType() throws SQLException;

    String getFuelIdByName(String name) throws SQLException;

    Double getFuelPriceByName(String name) throws SQLException;

    ArrayList<Double> getFuelPrices() throws SQLException;
}
