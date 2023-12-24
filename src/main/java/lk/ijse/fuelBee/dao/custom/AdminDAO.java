package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dto.AdminDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminDAO {

    AdminDto getAdmin(String email) throws SQLException, ClassNotFoundException;
    ArrayList<AdminDto> getAllAdmins() throws SQLException, ClassNotFoundException;
    boolean saveAdmin(AdminDto adminDto) throws SQLException, ClassNotFoundException;

    boolean updateAdmin(String email,String password) throws SQLException, ClassNotFoundException;



}
