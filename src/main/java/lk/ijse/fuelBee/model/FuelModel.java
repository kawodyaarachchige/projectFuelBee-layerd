package lk.ijse.fuelBee.model;

import lk.ijse.fuelBee.dao.impl.FuelDAOImpl;
import lk.ijse.fuelBee.dto.FuelTypeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class FuelModel {

    public static Double getFuelPriceByName(String name) throws SQLException {
        FuelDAOImpl fuelDAO = new FuelDAOImpl();
        Double fuelPriceByName = fuelDAO.getFuelPriceByName(name);
        if (fuelPriceByName != null) {
            return fuelPriceByName;
        } else {
            return null;
        }
    }
}


