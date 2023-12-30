package lk.ijse.fuelBee.dao.custom.impl;

import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.AdminDAO;
import lk.ijse.fuelBee.dto.EmployeeDto;
import lk.ijse.fuelBee.dto.MachineDto;
import lk.ijse.fuelBee.entity.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public ArrayList<Admin> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT email, CONVERT(AES_DECRYPT(password, 'fuelBee') USING utf8) AS decrypted_password, user_name, type FROM Admin");
        ArrayList<Admin> admin = new ArrayList<>();
        while (rs.next()) {
            admin.add(new Admin(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
        }
        return admin;
    }

    @Override
    public boolean delete(String entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Admin search(String entity) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Admin get(String entity) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Admin getAdmin(String email) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT email, CONVERT(AES_DECRYPT(password, 'fuelBee') USING utf8) AS decrypted_password, user_name, type FROM Admin WHERE email=?", email);
        Admin admin = new Admin();
        if (rst.next()) {
            admin.setEmail(rst.getString(1));
            admin.setPassword(rst.getString(2));
            admin.setUsername(rst.getString(3));
            admin.setType(rst.getString(4));
            return admin;
        } else {
            return null;
        }

       // return SQLUtil.execute("SELECT email, CONVERT(AES_DECRYPT(password, 'fuelBee') USING utf8) AS decrypted_password, user_name, type FROM Admin WHERE email=?", id);
    }



    @Override
    public boolean update(String email, String password) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Admin SET password=AES_ENCRYPT(?, 'fuelBee') WHERE email=?",password,email);
    }
    @Override
    public boolean save(Admin entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Admin VALUES(?,AES_ENCRYPT(?,'fuelBee'),?,?,) ,adminDto.getEmail(),adminDto.getPassword(),adminDto.getUsername(),adminDto.getType()");

    }

    @Override
    public boolean update(Admin dto) throws SQLException, ClassNotFoundException {
        return false;
    }
}
