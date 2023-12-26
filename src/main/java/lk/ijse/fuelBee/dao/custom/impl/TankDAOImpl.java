package lk.ijse.fuelBee.dao.custom.impl;

import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.TankDAO;
import lk.ijse.fuelBee.dto.EmployeeDto;
import lk.ijse.fuelBee.dto.MachineDto;
import lk.ijse.fuelBee.entity.Machine;
import lk.ijse.fuelBee.entity.Tank;

import java.sql.*;
import java.util.ArrayList;

public class TankDAOImpl implements TankDAO {
    @Override
    public  boolean save(Tank entity) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("INSERT INTO Tank VALUES(?,?,?,?,?,?)",entity.getTankId(),entity.getFuelType(),entity.getQty(),entity.getRemainingFuel(),entity.getCapacityOfWaste(),entity.getDate());

    }
   @Override
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Tank WHERE tank_id=?", id);

    }
  @Override
    public  boolean update(Tank entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Tank SET type=?,qty=?,remaining_fuel_in_tank=?,capacity_of_waste=?,date=? WHERE tank_id=?",entity.getFuelType(),entity.getQty(),entity.getRemainingFuel(),entity.getCapacityOfWaste(),entity.getDate(),entity.getTankId());

    }
    @Override
    public ArrayList<Tank> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Tank");
        ArrayList<Tank> tanks = new ArrayList<>();
        while (rst.next()) {
            tanks.add(new Tank(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4),
                    rst.getInt(5),
                    rst.getDate(6)
            ));
        }
        return tanks;
    }
   @Override
    public Tank search(String id) throws SQLException, ClassNotFoundException {
       ResultSet resultSet = SQLUtil.execute("SELECT * FROM Tank WHERE tank_id=?", id);

       if (resultSet.next()) {
           return new Tank(
                   resultSet.getString(1),
                   resultSet.getString(2),
                   resultSet.getInt(3),
                   resultSet.getInt(4),
                   resultSet.getInt(5),
                   resultSet.getDate(6)

           );
       } else {
           return null;
       }

   }

    @Override
    public Tank get(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}