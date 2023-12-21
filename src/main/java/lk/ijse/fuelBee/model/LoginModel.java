package lk.ijse.fuelBee.model;

import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.AdminDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    public static AdminDto searchAdmin(String email)throws SQLException {
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
