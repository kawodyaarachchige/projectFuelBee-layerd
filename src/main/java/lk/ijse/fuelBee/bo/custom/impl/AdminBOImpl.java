package lk.ijse.fuelBee.bo.custom.impl;

import lk.ijse.fuelBee.bo.custom.AdminBO;
import lk.ijse.fuelBee.dao.DAOFactory;
import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.AdminDAO;
import lk.ijse.fuelBee.dao.custom.EmployeeDAO;
import lk.ijse.fuelBee.dto.AdminDto;
import lk.ijse.fuelBee.entity.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminBOImpl implements AdminBO {
    AdminDAO adminDAO =(AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ADMIN);
    @Override
    public ArrayList<AdminDto> getAllAdmin() throws SQLException, ClassNotFoundException {
        ArrayList<Admin> admins = adminDAO.getAll();
        ArrayList<AdminDto> adminDtos = new ArrayList<>();
        for (Admin admin : admins) {
            adminDtos.add(new AdminDto(
                    admin.getEmail(),
                    admin.getPassword(),
                    admin.getUsername(),
                    admin.getType()
            ));
        }
        return adminDtos;

    }

    @Override
    public AdminDto getAdmin(String email) throws SQLException, ClassNotFoundException {
        Admin admin = adminDAO.getAdmin(email);
        return new AdminDto(admin.getEmail(), admin.getPassword(), admin.getUsername(), admin.getType());
       // return adminDAO.getAdmin(email);
    }

    @Override
    public boolean saveAdmin(AdminDto dto) throws SQLException, ClassNotFoundException {
        return adminDAO.save(new Admin(dto.getEmail(), dto.getPassword(), dto.getUsername(), dto.getType()));
    }


    @Override
    public boolean updateAdmin(String email, String password) throws SQLException, ClassNotFoundException {
        return adminDAO.updateAdmin(email, password);
        //return SQLUtil.execute("UPDATE Admin SET password=AES_ENCRYPT(?, 'fuelBee') WHERE email=?",password,email);
    }
}

