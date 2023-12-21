package lk.ijse.fuelBee.model;

import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {
    public static ArrayList<SupplierDto> getAllSuppliers() throws SQLException {
        ArrayList<SupplierDto> allSuppliers = new ArrayList<>();
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT * FROM Supplier";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet rst = pstm.executeQuery();
        while (rst.next()) {
            allSuppliers.add(new SupplierDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5), rst.getString(6)));
        }
        return allSuppliers;
    }

    public static boolean saveSupplier(SupplierDto supplierDto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();

        String sql = "INSERT INTO Supplier VALUES(?,?,?,?,?,?) ";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, supplierDto.getSupId());
        pstm.setString(2, supplierDto.getName());
        pstm.setString(3, supplierDto.getFuelType());
        pstm.setInt(4, supplierDto.getContact());
        pstm.setString(5, supplierDto.getAddress());
        pstm.setString(6, supplierDto.getSup_email());

        if (pstm.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean deleteSupplier(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql="DELETE FROM Supplier WHERE sup_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);

        if(pstm.executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public static boolean updateSupplier(SupplierDto supplierDto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql="UPDATE Supplier SET name=?,fuel_type=?,contact_number=?,address=?,sup_email=? WHERE sup_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, supplierDto.getName());
        pstm.setString(2, supplierDto.getFuelType());
        pstm.setInt(3, supplierDto.getContact());
        pstm.setString(4, supplierDto.getAddress());
        pstm.setString(5, supplierDto.getSup_email());
        pstm.setString(6, supplierDto.getSupId());

        if(pstm.executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }
    public static SupplierDto getSupplier(String id) throws SQLException {
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
    }

}
