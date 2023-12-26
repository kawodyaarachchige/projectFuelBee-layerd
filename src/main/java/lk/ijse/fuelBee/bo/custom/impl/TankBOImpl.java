package lk.ijse.fuelBee.bo.custom.impl;

import lk.ijse.fuelBee.bo.custom.TankBO;
import lk.ijse.fuelBee.dao.DAOFactory;
import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.TankDAO;
import lk.ijse.fuelBee.dto.SupplierDto;
import lk.ijse.fuelBee.dto.TankDto;
import lk.ijse.fuelBee.entity.Supplier;
import lk.ijse.fuelBee.entity.Tank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TankBOImpl implements TankBO {

    TankDAO tankDAO = (TankDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.TANK);
    @Override
    public  boolean saveTank(TankDto dto) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("INSERT INTO Tank VALUES(?,?,?,?,?,?)",dto.getTankId(),dto.getFuelType(),dto.getQty(),dto.getRemainingFuel(),dto.getCapacityOfWaste(),dto.getDate());

    }
   @Override
    public  boolean deleteTank(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Tank WHERE tank_id=?", id);

    }
  @Override
    public  boolean updateTank(TankDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Tank SET type=?,qty=?,remaining_fuel_in_tank=?,capacity_of_waste=?,date=? WHERE tank_id=?",dto.getFuelType(),dto.getQty(),dto.getRemainingFuel(),dto.getCapacityOfWaste(),dto.getDate(),dto.getTankId());

    }
    @Override
    public ArrayList<TankDto> getAllTanks() throws SQLException, ClassNotFoundException {
        ArrayList<Tank> tanks = tankDAO.getAll();
        ArrayList<TankDto> tankDTOS = new ArrayList<>();
        for (Tank tank : tanks) {
            tankDTOS.add(new TankDto(
                    tank.getTankId(),
                    tank.getFuelType(),
                    tank.getQty(),
                    tank.getRemainingFuel(),
                    tank.getCapacityOfWaste(),
                    tank.getDate()
            ));
        }
        return tankDTOS;
    }
   @Override
    public TankDto searchTank(String id) throws SQLException, ClassNotFoundException {
       // return SQLUtil.execute("SELECT * FROM Tank WHERE tank_id=?", id);
       Tank search = tankDAO.search(id);
       return new TankDto(search.getTankId(),search.getFuelType(),search.getQty(),search.getRemainingFuel(),search.getCapacityOfWaste(),search.getDate());

    }

}
