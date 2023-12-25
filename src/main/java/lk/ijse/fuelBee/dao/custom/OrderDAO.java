package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dto.OrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO {
    boolean saveOrder(OrderDto orderDto) throws SQLException, ClassNotFoundException;

    boolean deleteOrder(String id) throws SQLException, ClassNotFoundException;

    boolean updateOrder(OrderDto orderDto) throws SQLException, ClassNotFoundException;
    ArrayList<OrderDto> getAllOrders() throws SQLException, ClassNotFoundException;
    int getOrderCount() throws SQLException, ClassNotFoundException;

    OrderDto getOrderDetails(String id) throws SQLException, ClassNotFoundException;
}
