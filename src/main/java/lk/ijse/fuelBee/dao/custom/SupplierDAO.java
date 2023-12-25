package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO {
    ArrayList<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException;
    boolean saveSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException;
    boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;

    boolean updateSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException;
}
