package lk.ijse.fuelBee.bo.custom;

import lk.ijse.fuelBee.bo.SuperBO;
import lk.ijse.fuelBee.dao.CrudDAO;
import lk.ijse.fuelBee.dto.AdminDto;
import lk.ijse.fuelBee.dto.OrderDto;
import lk.ijse.fuelBee.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    boolean saveOrder(OrderDto orderDto) throws SQLException, ClassNotFoundException;

    boolean deleteOrder(String id) throws SQLException, ClassNotFoundException;

    boolean updateOrder(OrderDto orderDto) throws SQLException, ClassNotFoundException;
    ArrayList<OrderDto> getAllOrder() throws SQLException, ClassNotFoundException;
    int getOrderCount() throws SQLException, ClassNotFoundException;

   // ArrayList<AdminDto> getAll() throws SQLException, ClassNotFoundException;
    OrderDto getOrderDetails(String id) throws SQLException, ClassNotFoundException;

    boolean updateOrderStatus(String id, String status) throws SQLException, ClassNotFoundException;


}
