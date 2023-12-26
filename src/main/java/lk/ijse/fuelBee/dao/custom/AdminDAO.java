package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dao.CrudDAO;
import lk.ijse.fuelBee.entity.Admin;

import java.sql.SQLException;

public interface AdminDAO extends CrudDAO <Admin>{

    Admin getAdmin(String email) throws SQLException, ClassNotFoundException;

    boolean save(Admin entity) throws SQLException, ClassNotFoundException;

    boolean updateAdmin(String email,String password) throws SQLException, ClassNotFoundException;

}
