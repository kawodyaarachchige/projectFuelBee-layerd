package lk.ijse.fuelBee.model;

import lk.ijse.fuelBee.dao.SupplierDAOImpl;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {
    public static ArrayList<SupplierDto> getAllSuppliers() throws SQLException {
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
        ArrayList<SupplierDto> allSuppliers = supplierDAO.getAllSuppliers();
        if(allSuppliers != null){
            return allSuppliers;
        }else{
            return null;
        }
    }

    public static boolean saveSupplier(SupplierDto supplierDto) throws SQLException {
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
        boolean saved = supplierDAO.saveSupplier(supplierDto);
        if(saved){
            return true;
        }else{
            return false;
        }
    }

    public static boolean deleteSupplier(String id) throws SQLException {
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
        boolean deleted = supplierDAO.deleteSupplier(id);
        if(deleted){
            return true;
        }else{
            return false;
        }
    }

    public static boolean updateSupplier(SupplierDto supplierDto) throws SQLException {
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl();
        boolean updated = supplierDAO.updateSupplier(supplierDto);
        if(updated){
            return true;
        }else {
            return false;
        }
    }
  /*  public static SupplierDto getSupplier(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM Supplier WHERE sup_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        SupplierDto supplierDto = new SupplierDto();
        ResultSet rst = pstm.executeQuery();
        while (rst.next()) {
            supplierDto.setSupId(rst.getString(1));
            supplierDto.setName(rst.getString(2));
            supplierDto.setFuelType(rst.getString(3));
            supplierDto.setContact(rst.getInt(4));
            supplierDto.setAddress(rst.getString(5));
            supplierDto.setSup_email(rst.getString(6));
        }return supplierDto;
    }

    public static String getSupplierIdByName(String name) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT sup_id FROM Supplier WHERE name=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,name);

        ResultSet rst = pstm.executeQuery();
        if(rst.next()){
            return rst.getString(1);
        }else{
            return null;
        }
    }*/

}
