package lk.ijse.fuelBee.model;

import lk.ijse.fuelBee.dao.EmployeeDAOImpl;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeModel {
    public static boolean saveEmployee(EmployeeDto dto)throws SQLException {

        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        boolean isSaved = employeeDAO.saveEmployee(dto);

        if(isSaved){
            return true;
        }else{
            return false;
        }
    }

    public static boolean deleteEmployee(String id)throws SQLException {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        boolean isDeleted = employeeDAO.deleteEmployee(id);
        if(isDeleted){
            return true;
        }else{
            return false;
        }
    }

    public static boolean updateEmployee(EmployeeDto dto)throws SQLException {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        boolean isUpdated = employeeDAO.updateEmployee(dto);
        if(isUpdated){
            return true;
        }else{
            return false;
        }
    }

    /*public EmployeeDto searchEmployee(String id)throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql="select * from Employee where emp_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);

        ResultSet resultSet=pstm.executeQuery();
        if(resultSet!=null){
            return new EmployeeDto(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getDouble(6),resultSet.getString(7),resultSet.getString(8));
        }else{
            return null;

        }
    }*/
    public static ArrayList<EmployeeDto> getAllEmployees()throws SQLException{
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        ArrayList<EmployeeDto> allEmployees = employeeDAO.getAllEmployees();
        return allEmployees;
    }

}
