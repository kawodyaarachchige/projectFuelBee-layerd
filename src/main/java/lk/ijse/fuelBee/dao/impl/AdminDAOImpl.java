package lk.ijse.fuelBee.dao.impl;

import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.AdminDAO;
import lk.ijse.fuelBee.dto.AdminDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public AdminDto getAdmin(String email) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT email, CONVERT(AES_DECRYPT(password, 'fuelBee') USING utf8) AS decrypted_password, user_name, type FROM Admin WHERE email=?", email);
        AdminDto adminDto = new AdminDto();
        if (rst.next()) {
            adminDto.setEmail(rst.getString(1));
            adminDto.setPassword(rst.getString(2));
            adminDto.setUsername(rst.getString(3));
            adminDto.setType(rst.getString(4));
            return adminDto;
        } else {
            return null;
        }
    }
@Override
    public ArrayList<AdminDto> getAll() throws SQLException, ClassNotFoundException {
    ResultSet rs = SQLUtil.execute("SELECT email, CONVERT(AES_DECRYPT(password, 'fuelBee') USING utf8) AS decrypted_password, user_name, type FROM Admin");
         ArrayList<AdminDto> adminDto = new ArrayList<>();
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
    @Override
    public boolean save(AdminDto adminDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Admin VALUES(?,AES_ENCRYPT(?,'fuelBee'),?,?,) ,adminDto.getEmail(),adminDto.getPassword(),adminDto.getUsername(),adminDto.getType()");

    }

    @Override
    public boolean update(AdminDto dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public AdminDto search(String id) throws SQLException {
        return null;
    }

    @Override
    public AdminDto get(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean updateAdmin(String email, String password) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Admin SET password=AES_ENCRYPT(?, 'fuelBee') WHERE email=?",password,email);
    }

}



