package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dao.CrudDAO;
import lk.ijse.fuelBee.dto.AdminDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminDAO extends CrudDAO <AdminDto>{

    AdminDto getAdmin(String email) throws SQLException, ClassNotFoundException;



    boolean updateAdmin(String email,String password) throws SQLException, ClassNotFoundException;

}
