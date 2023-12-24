package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dto.TankDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TankDAO {
    boolean saveTank(TankDto tankDto) throws SQLException;
    boolean deleteTank(String id) throws SQLException;
    boolean updateTank(TankDto tankDto) throws SQLException;
    ArrayList<TankDto> getAllTank() throws SQLException;
    TankDto searchTank(String id) throws SQLException;
}
