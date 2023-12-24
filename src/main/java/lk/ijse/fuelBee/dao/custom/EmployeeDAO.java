package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO {

    boolean saveEmployee(EmployeeDto dto) throws SQLException;
    boolean deleteEmployee(String id) throws SQLException;

    boolean updateEmployee(EmployeeDto dto) throws SQLException;
    ArrayList<EmployeeDto> getAllEmployees() throws SQLException;
    int getEmployeeCount() throws SQLException;

}
