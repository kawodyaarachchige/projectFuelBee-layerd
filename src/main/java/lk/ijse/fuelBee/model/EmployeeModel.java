package lk.ijse.fuelBee.model;

import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeModel {
    public static boolean saveEmployee(EmployeeDto dto)throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql="insert into Employee values(?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getId());
        pstm.setString(2,dto.getFirstName());
        pstm.setString(3,dto.getLastName());
        pstm.setString(4,dto.getAddress());
        pstm.setInt(5,dto.getAge());
        pstm.setDouble(6,dto.getSalary());
        pstm.setString(7,dto.getJobTitle());
        pstm.setString(8,dto.getEmail());

        if(pstm.executeUpdate()>0){
            return true;
        }else {
            return false;
        }
    }

    public static boolean deleteEmployee(String id)throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql="DELETE FROM Employee WHERE emp_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);

        if(pstm.executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public static boolean updateEmployee(EmployeeDto dto)throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql="update Employee set first_name=?,last_name=?,address=?,age=?,salary=?,role=?,email=? where emp_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getFirstName());
        pstm.setString(2,dto.getLastName());
        pstm.setString(3,dto.getAddress());
        pstm.setInt(4,dto.getAge());
        pstm.setDouble(5,dto.getSalary());
        pstm.setString(6,dto.getJobTitle());
        pstm.setString(7,dto.getEmail());
        pstm.setString(8,dto.getId());

        if(pstm.executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public EmployeeDto searchEmployee(String id)throws SQLException {
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
    }
    public static ArrayList<EmployeeDto> getAllEmployees()throws SQLException{
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql="select * from Employee";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet=pstm.executeQuery();

        ArrayList<EmployeeDto> list=new ArrayList<>();
        while (resultSet.next()){
            list.add(new EmployeeDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getDouble(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            ));
        }return list;
    }
}
