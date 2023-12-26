package lk.ijse.fuelBee.bo.custom;

import lk.ijse.fuelBee.bo.SuperBO;
import lk.ijse.fuelBee.dto.AdminDto;
import lk.ijse.fuelBee.entity.Admin;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminBO extends SuperBO {

    ArrayList<AdminDto> getAllAdmin() throws SQLException, ClassNotFoundException;

    AdminDto getAdmin(String email) throws SQLException, ClassNotFoundException;

    boolean saveAdmin(AdminDto dto) throws SQLException, ClassNotFoundException;

    boolean updateAdmin (String email,String password) throws SQLException, ClassNotFoundException;


}
