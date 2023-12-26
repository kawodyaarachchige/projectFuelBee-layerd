package lk.ijse.fuelBee.bo.custom.impl;

import lk.ijse.fuelBee.bo.custom.EmployeeBO;
import lk.ijse.fuelBee.dao.DAOFactory;
import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.EmployeeDAO;
import lk.ijse.fuelBee.dto.EmployeeDto;
import lk.ijse.fuelBee.dto.MachineDto;
import lk.ijse.fuelBee.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EMPLOYEE);
   @Override
    public boolean saveEmployee (EmployeeDto dto) throws SQLException, ClassNotFoundException {
       return employeeDAO .save(new Employee(dto.getId(),dto.getFirstName(),dto.getLastName(),dto.getAddress(),dto.getAge(),dto.getSalary(),dto.getJobTitle(),dto.getEmail()));
      // return SQLUtil.execute("INSERT INTO Employee VALUES(?,?,?,?,?,?,?,?)",dto.getId(),dto.getFirstName(),dto.getLastName(),dto.getAddress(),dto.getAge(),dto.getSalary(),dto.getJobTitle(),dto.getEmail());

    }


    @Override
    public boolean updateEmployee (EmployeeDto dto) throws SQLException, ClassNotFoundException {
       return employeeDAO.update(new Employee(dto.getId(),dto.getFirstName(),dto.getLastName(),dto.getAddress(),dto.getAge(),dto.getSalary(),dto.getJobTitle(),dto.getEmail()));
      // return SQLUtil.execute("update Employee set first_name=?,last_name=?,address=?,age=?,salary=?,role=?,email=? where emp_id=?",dto.getFirstName(),dto.getLastName(),dto.getAddress(),dto.getAge(),dto.getSalary(),dto.getJobTitle(),dto.getEmail(),dto.getId());

    }
    @Override
    public ArrayList<EmployeeDto> getAllEmployee() throws SQLException, ClassNotFoundException {
       ArrayList<Employee> employees = employeeDAO.getAll();
       ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();
       for (Employee employee : employees) {
           employeeDtos.add(new EmployeeDto(
                   employee.getId(),
                   employee.getFirstName(),
                   employee.getLastName(),
                   employee.getAddress(),
                   employee.getAge(),
                   employee.getSalary(),
                   employee.getJobTitle(),
                   employee.getEmail()
           ));
       }
       return employeeDtos;

    }
    @Override
    public int getEmployeeCount() throws SQLException, ClassNotFoundException {
       return employeeDAO.getEmployeeCount();
    /*   ResultSet rs = SQLUtil.execute("select count(*) from Employee");
        try {
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;*/
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
       return employeeDAO.delete(id);
        //return SQLUtil.execute("DELETE FROM Employee WHERE emp_id=?",id);
    }
}
