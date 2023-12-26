package lk.ijse.fuelBee.bo.custom.impl;

import lk.ijse.fuelBee.bo.custom.SupplierBO;
import lk.ijse.fuelBee.dao.DAOFactory;
import lk.ijse.fuelBee.dao.custom.SupplierDAO;
import lk.ijse.fuelBee.dto.SupplierDto;
import lk.ijse.fuelBee.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIER);
   @Override
    public ArrayList<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException {
       ArrayList<Supplier> suppliers = supplierDAO.getAll();
       ArrayList<SupplierDto> supplierDTOS = new ArrayList<>();
       for (Supplier supplier : suppliers) {
           supplierDTOS.add(new SupplierDto(supplier.getSupId(), supplier.getName(), supplier.getFuelType(), supplier.getContact(), supplier.getAddress(), supplier.getSup_email()));
       }
       return supplierDTOS;
    }

    @Override
    public boolean saveSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {
       return supplierDAO.save(new Supplier(dto.getSupId(), dto.getName(), dto.getFuelType(), dto.getContact(), dto.getAddress(), dto.getSup_email()));
      //return SQLUtil.execute("INSERT INTO Supplier VALUES(?,?,?,?,?,?) ", dto.getSupId(), dto.getName(), dto.getFuelType(), dto.getContact(), dto.getAddress(), dto.getSup_email());
    }
   @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
       return supplierDAO.delete(id);
      // return SQLUtil.execute("DELETE FROM Supplier WHERE sup_id=?", id);

    }

    @Override
    public boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {
       return  supplierDAO.update(new Supplier(dto.getSupId(), dto.getName(), dto.getFuelType(), dto.getContact(), dto.getAddress(), dto.getSup_email()));
      // return SQLUtil.execute("UPDATE Supplier SET name=?,fuel_type=?,contact_number=?,address=?,sup_email=? WHERE sup_id=?", dto.getName(), dto.getFuelType(), dto.getContact(), dto.getAddress(), dto.getSup_email(), dto.getSupId());

    }
}
