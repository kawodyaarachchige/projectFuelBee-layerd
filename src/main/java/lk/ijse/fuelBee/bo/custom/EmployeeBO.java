package lk.ijse.fuelBee.bo.custom;

import lk.ijse.fuelBee.bo.SuperBO;
import lk.ijse.fuelBee.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {


    boolean saveEmployee (EmployeeDto dto) throws SQLException, ClassNotFoundException;

    boolean updateEmployee (EmployeeDto dto) throws SQLException, ClassNotFoundException;

    ArrayList<EmployeeDto> getAllEmployee () throws SQLException, ClassNotFoundException;


    int getEmployeeCount () throws SQLException, ClassNotFoundException;

    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;

}
