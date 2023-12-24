package lk.ijse.fuelBee.dao;

import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.AdminDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDAOImpl {
    public AdminDto getAdmin(String email) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT email, " +
                "CONVERT(AES_DECRYPT(password, 'fuelBee') USING utf8) AS decrypted_password, " +
                "user_name, type " +
                "FROM Admin " +
                "WHERE email=?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, email);

        ResultSet rst = pstm.executeQuery();
        AdminDto adminDto = new AdminDto();
        if (rst.next()) {
            adminDto.setEmail(rst.getString(1));
            adminDto.setPassword(rst.getString(2));
            adminDto.setUsername(rst.getString(3));
            adminDto.setType(rst.getString(4));
            return adminDto;
        }else {
            return null;
        }
    }
    public ArrayList<AdminDto> getAllAdmins() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "SELECT email, CONVERT(AES_DECRYPT(password, 'fuelBee') USING utf8) AS decrypted_password, user_name, type FROM Admin";

        PreparedStatement pstm = connection.prepareStatement(sql);
        ArrayList<AdminDto> adminDto = new ArrayList<>();
        ResultSet rs = pstm.executeQuery(sql);
        while (rs.next()) {
            adminDto.add(new AdminDto(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
            ));
        }
        return adminDto;
    }
    public boolean saveAdmin(AdminDto adminDto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "INSERT INTO Admin VALUES(?,AES_ENCRYPT(?,'fuelBee'),?,?) ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, adminDto.getEmail());
        pstm.setString(2, adminDto.getPassword());
        pstm.setString(3, adminDto.getUsername());
        pstm.setString(4, "ADMIN");

        if (pstm.executeUpdate() > 0) {
            return true;
        }else{
            return false;
        }
    }
    public boolean updateAdmin(String email,String password) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql = "UPDATE Admin SET password=AES_ENCRYPT(?, 'fuelBee') WHERE email=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, password);
        pstm.setString(2, email);
        if (pstm.executeUpdate() > 0) {
            return true;
        }else{
            return false;
        }
    }
    public AdminDto searchAdmin(String email) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql="select * from Admin where email=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,email);

        ResultSet resultSet=pstm.executeQuery();
        AdminDto dto=null;
        if(resultSet.next()){
            dto = new AdminDto(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
        }
        return dto;
    }

}



