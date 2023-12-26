package lk.ijse.fuelBee.bo.custom;

import lk.ijse.fuelBee.bo.SuperBO;
import lk.ijse.fuelBee.dao.CrudDAO;
import lk.ijse.fuelBee.dto.SupplierDto;
import lk.ijse.fuelBee.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    ArrayList<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException;

    boolean saveSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;

    boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException;
   //  ArrayList<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException;
  //  boolean saveSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException;
  //  boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;

  //  boolean updateSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException;
}
