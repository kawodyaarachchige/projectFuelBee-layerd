package lk.ijse.fuelBee.model;

import lk.ijse.fuelBee.dao.TankDAOImpl;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.TankDto;

import java.sql.*;
import java.util.ArrayList;

public class TankModel {
    public static boolean saveTank(TankDto tankDto) throws SQLException {
        TankDAOImpl tankDAO = new TankDAOImpl();
        boolean saved = tankDAO.saveTank(tankDto);
        if(saved){
            return true;
        }else{
            return false;
        }
    }

    public static boolean deleteTank(String id) throws SQLException {
        TankDAOImpl tankDAO = new TankDAOImpl();
        boolean deleted = tankDAO.deleteTank(id);
        if(deleted){
            return true;
        }else{
            return false;
        }
    }

    public static boolean updateTank(TankDto tankDto) throws SQLException {
        TankDAOImpl tankDAO = new TankDAOImpl();
        boolean updated = tankDAO.updateTank(tankDto);
        if(updated){
            return true;
        }else {
            return false;
        }
    }

    public static ArrayList<TankDto> getAllTank() throws SQLException {
        TankDAOImpl tankDAO = new TankDAOImpl();
        ArrayList<TankDto> allTank = tankDAO.getAllTank();
        if (allTank != null) {
            return allTank;
        } else {
            return null;
        }
    }
    public static TankDto searchTank(String id) throws SQLException {
        TankDAOImpl tankDAO = new TankDAOImpl();
        TankDto tankDto = tankDAO.searchTank(id);
        if(tankDto != null){
            return tankDto;
        }else{
            return null;
        }
    }
}
