package lk.ijse.fuelBee.model;

import javafx.scene.control.Alert;
import lk.ijse.fuelBee.dao.FuelDAOImpl;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.FuelDto;
import lk.ijse.fuelBee.dto.FuelTypeDto;
import lk.ijse.fuelBee.dto.MachineDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuelModel {
    public static ArrayList<FuelTypeDto> getAllFuelType() throws SQLException {
        FuelDAOImpl fuelDAO = new FuelDAOImpl();
        ArrayList<FuelTypeDto> allFuels = fuelDAO.getAllFuels();
        return allFuels;
    }


    public static String getFuelIdByName(String name) throws SQLException {
        FuelDAOImpl fuelDAO = new FuelDAOImpl();
        String fuelIdByName = fuelDAO.getFuelIdByName(name);
        if (fuelIdByName != null) {
            return fuelIdByName;
        } else {
            return null;
        }
    }
    public static Double getFuelPriceByName(String name) throws SQLException {
        FuelDAOImpl fuelDAO = new FuelDAOImpl();
        Double fuelPriceByName = fuelDAO.getFuelPriceByName(name);
        if(fuelPriceByName != null){
            return fuelPriceByName;
        }else{
            return null;
        }
    }

    public static ArrayList<Double> getFuelPrices() throws SQLException {
        FuelDAOImpl fuelDAO = new FuelDAOImpl();
        ArrayList<Double> fuelPrices = fuelDAO.getFuelPrices();
        if (fuelPrices != null) {
            return fuelPrices;
        } else {
            return null;
        }
    }
}
