package lk.ijse.fuelBee.dao.custom.impl;

import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.EmployeeDAO;
import lk.ijse.fuelBee.dto.EmployeeDto;
import lk.ijse.fuelBee.dto.MachineDto;
import lk.ijse.fuelBee.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
   @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("INSERT INTO Employee VALUES(?,?,?,?,?,?,?,?)",entity.getId(),entity.getFirstName(),entity.getLastName(),entity.getAddress(),entity.getAge(),entity.getSalary(),entity.getJobTitle(),entity.getEmail());

    }
   @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Employee WHERE emp_id=?",id);

    }

    @Override
    public Employee search(String id) throws SQLException {
        return null;
    }

    @Override
    public Employee get(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("update Employee set first_name=?,last_name=?,address=?,age=?,salary=?,role=?,email=? where emp_id=?",entity.getFirstName(),entity.getLastName(),entity.getAddress(),entity.getAge(),entity.getSalary(),entity.getJobTitle(),entity.getEmail(),entity.getId());

    }
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Employee");
        ArrayList<Employee> list=new ArrayList<>();
        while (resultSet.next()){
            list.add(new Employee(
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
