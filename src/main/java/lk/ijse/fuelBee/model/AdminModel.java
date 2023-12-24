package lk.ijse.fuelBee.model;

import lk.ijse.fuelBee.dao.AdminDAOImpl;
import lk.ijse.fuelBee.dao.FuelDAOImpl;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.AdminDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminModel {
    public static AdminDto getAdmin(String email) throws SQLException {
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        AdminDto admin = adminDAO.getAdmin(email);
        return admin;

    }

    public static ArrayList<AdminDto> getAllAdmins() throws SQLException {
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        ArrayList<AdminDto> allAdmins = adminDAO.getAllAdmins();
        return allAdmins;
    }
    public static boolean saveAdmin(AdminDto adminDto) throws SQLException {
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        boolean isSaved = adminDAO.saveAdmin(adminDto);
        if(isSaved){
            return true;
        }else{
            return false;
        }
    }

    public static boolean updateAdmin(String email,String password) throws SQLException {
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        boolean isUpdated = adminDAO.updateAdmin(email, password);
        if(isUpdated){
            return true;
        }else{
            return false;
        }
    }
}
