package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dto.OrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO {
    boolean saveOrder(OrderDto orderDto) throws SQLException;

    boolean deleteOrder(String id) throws SQLException;

    boolean updateOrder(OrderDto orderDto) throws SQLException;
    ArrayList<OrderDto> getAllOrders() throws SQLException;
    int getOrderCount() throws SQLException;

    OrderDto getOrderDetails(String id) throws SQLException;
}
