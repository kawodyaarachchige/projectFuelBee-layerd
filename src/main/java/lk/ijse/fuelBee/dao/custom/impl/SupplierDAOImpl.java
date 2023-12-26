package lk.ijse.fuelBee.dao.custom.impl;

import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.SupplierDAO;
import lk.ijse.fuelBee.dto.EmployeeDto;
import lk.ijse.fuelBee.dto.MachineDto;
import lk.ijse.fuelBee.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
   @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> allSuppliers = new ArrayList<>();
       ResultSet rst = SQLUtil.execute("SELECT * FROM Supplier");
        while (rst.next()) {
            allSuppliers.add(new Supplier(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5), rst.getString(6)));
        }
        return allSuppliers;
    }
    @Override
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
      return SQLUtil.execute("INSERT INTO Supplier VALUES(?,?,?,?,?,?) ", entity.getSupId(), entity.getName(), entity.getFuelType(), entity.getContact(), entity.getAddress(), entity.getSup_email());
    }
   @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("DELETE FROM Supplier WHERE sup_id=?", id);

    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Supplier get(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("UPDATE Supplier SET name=?,fuel_type=?,contact_number=?,address=?,sup_email=? WHERE sup_id=?", entity.getName(), entity.getFuelType(), entity.getContact(), entity.getAddress(), entity.getSup_email(), entity.getSupId());

    }
}
