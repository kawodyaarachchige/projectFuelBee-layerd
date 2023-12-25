package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dao.CrudDAO;
import lk.ijse.fuelBee.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<EmployeeDto> {

   // boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException;
   // boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;

   // boolean updateEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException;
   // ArrayList<EmployeeDto> getAllEmployees() throws SQLException, ClassNotFoundException;
    int getEmployeeCount() throws SQLException, ClassNotFoundException;

}
