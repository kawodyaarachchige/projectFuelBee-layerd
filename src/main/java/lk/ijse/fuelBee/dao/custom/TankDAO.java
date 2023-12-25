package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dto.TankDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TankDAO {
    boolean save(TankDto tankDto) throws SQLException;

    boolean delete(String id) throws SQLException;

    boolean update(TankDto tankDto) throws SQLException;

    ArrayList<TankDto> getAll() throws SQLException, ClassNotFoundException;

    TankDto search(String id) throws SQLException, ClassNotFoundException;
    // boolean saveTank(TankDto tankDto) throws SQLException;
  //  boolean deleteTank(String id) throws SQLException;
   // boolean updateTank(TankDto tankDto) throws SQLException;
   // ArrayList<TankDto> getAllTank() throws SQLException, ClassNotFoundException;
   // TankDto searchTank(String id) throws SQLException, ClassNotFoundException;
}
