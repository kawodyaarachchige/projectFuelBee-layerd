package lk.ijse.fuelBee.dao.impl;

import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.EmployeeDAO;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
   @Override
    public boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("INSERT INTO Employee VALUES(?,?,?,?,?,?,?,?)",dto.getId(),dto.getFirstName(),dto.getLastName(),dto.getAddress(),dto.getAge(),dto.getSalary(),dto.getJobTitle(),dto.getEmail());

    }
   @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Employee WHERE emp_id=?",id);

    }
    @Override
    public boolean updateEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("update Employee set first_name=?,last_name=?,address=?,age=?,salary=?,role=?,email=? where emp_id=?",dto.getFirstName(),dto.getLastName(),dto.getAddress(),dto.getAge(),dto.getSalary(),dto.getJobTitle(),dto.getEmail(),dto.getId());

    }
    @Override
    public ArrayList<EmployeeDto> getAllEmployees() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Employee");
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
   @Override
    public int getEmployeeCount() throws SQLException, ClassNotFoundException {
       ResultSet rs = SQLUtil.execute("select count(*) from Employee");
        try {
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
