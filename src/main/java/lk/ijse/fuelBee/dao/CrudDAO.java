package lk.ijse.fuelBee.dao;

import lk.ijse.fuelBee.dto.EmployeeDto;
import lk.ijse.fuelBee.dto.MachineDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T> extends SuperDAO{

         ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

         boolean save(T dto) throws SQLException, ClassNotFoundException;

         boolean update(T dto) throws SQLException, ClassNotFoundException;

         boolean delete(String id) throws SQLException, ClassNotFoundException;

         T search(String id) throws SQLException, ClassNotFoundException;

         T get(String id) throws SQLException, ClassNotFoundException;

     }

