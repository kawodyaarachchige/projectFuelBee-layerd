package lk.ijse.fuelBee.model;

import lk.ijse.fuelBee.dao.EmployeeDAOImpl;
import lk.ijse.fuelBee.dao.OrderDAOImpl;
import lk.ijse.fuelBee.db.Dbconnection;
import lk.ijse.fuelBee.dto.OrderDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderModel {

    public static boolean saveOrder(OrderDto orderDto) throws SQLException {
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        boolean saved = orderDAO.saveOrder(orderDto);
        if(saved){
            return true;
        }else{
            return false;
        }
    }
    public static boolean deleteOrder(String id) throws SQLException {
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        boolean deleted = orderDAO.deleteOrder(id);
        if (deleted) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean updateOrder(OrderDto orderDto) throws SQLException {
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        boolean updated = orderDAO.updateOrder(orderDto);
        if (updated) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<OrderDto> getAllOrders() throws SQLException {
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        ArrayList<OrderDto> allOrders = orderDAO.getAllOrders();
        if (allOrders != null) {
            return allOrders;
        } else {
            return null;
        }
    }

    public static int getOrderCount() throws SQLException {
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        int orderCount = orderDAO.getOrderCount();
        if (orderCount != 0) {
            return orderCount;
        }else{
            return 0;
        }
    }


    public static int getEmployeeCount() throws SQLException {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        int employeeCount = employeeDAO.getEmployeeCount();
        if(employeeCount!=0){
            return employeeCount;
        }else{
            return 0;
        }
    }
    public static OrderDto getOrderDetails(String id) throws SQLException {
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        OrderDto orderDetails = orderDAO.getOrderDetails(id);
        if(orderDetails!=null){
            return orderDetails;
        }else{
            return null;
        }
    }
}


