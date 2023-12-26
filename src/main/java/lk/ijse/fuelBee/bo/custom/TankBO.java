package lk.ijse.fuelBee.bo.custom;

import lk.ijse.fuelBee.bo.SuperBO;
import lk.ijse.fuelBee.dto.TankDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TankBO extends SuperBO {
    boolean saveTank(TankDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteTank(String id) throws SQLException, ClassNotFoundException;

    boolean updateTank(TankDto dto) throws SQLException, ClassNotFoundException;

    ArrayList<TankDto> getAllTanks() throws SQLException, ClassNotFoundException;

    TankDto searchTank(String id) throws SQLException, ClassNotFoundException;

    //Tank get(String id) throws SQLException;
    // boolean save(Tank entity) throws SQLException, ClassNotFoundException;

   // boolean delete(String id) throws SQLException, ClassNotFoundException;

   // boolean update(Tank entity) throws SQLException, ClassNotFoundException;

   // ArrayList<Tank> getAll() throws SQLException, ClassNotFoundException;

   // Tank search(String id) throws SQLException, ClassNotFoundException;
    // boolean saveTank(TankDto tankDto) throws SQLException;
  //  boolean deleteTank(String id) throws SQLException;
   // boolean updateTank(TankDto tankDto) throws SQLException;
   // ArrayList<TankDto> getAllTank() throws SQLException, ClassNotFoundException;
   // TankDto searchTank(String id) throws SQLException, ClassNotFoundException;
}
