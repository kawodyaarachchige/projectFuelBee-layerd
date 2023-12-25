package lk.ijse.fuelBee.dao.impl;

import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.SupplierDAO;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
   @Override
    public ArrayList<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDto> allSuppliers = new ArrayList<>();
       ResultSet rst = SQLUtil.execute("SELECT * FROM Supplier");
        while (rst.next()) {
            allSuppliers.add(new SupplierDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5), rst.getString(6)));
        }
        return allSuppliers;
    }
    @Override
    public boolean saveSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
      return SQLUtil.execute("INSERT INTO Supplier VALUES(?,?,?,?,?,?) ", supplierDto.getSupId(), supplierDto.getName(), supplierDto.getFuelType(), supplierDto.getContact(), supplierDto.getAddress(), supplierDto.getSup_email());
    }
   @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("DELETE FROM Supplier WHERE sup_id=?", id);

    }
    @Override
    public boolean updateSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("UPDATE Supplier SET name=?,fuel_type=?,contact_number=?,address=?,sup_email=? WHERE sup_id=?", supplierDto.getName(), supplierDto.getFuelType(), supplierDto.getContact(), supplierDto.getAddress(), supplierDto.getSup_email(), supplierDto.getSupId());

    }
}
