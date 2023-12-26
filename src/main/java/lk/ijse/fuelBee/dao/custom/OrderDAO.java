package lk.ijse.fuelBee.dao.custom;

import lk.ijse.fuelBee.dao.CrudDAO;
import lk.ijse.fuelBee.entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order> {
   // boolean saveOrder(OrderDto orderDto) throws SQLException, ClassNotFoundException;

  //  boolean deleteOrder(String id) throws SQLException, ClassNotFoundException;

   // boolean updateOrder(OrderDto orderDto) throws SQLException, ClassNotFoundException;
   // ArrayList<OrderDto> getAllOrders() throws SQLException, ClassNotFoundException;
    int getOrderCount() throws SQLException, ClassNotFoundException;

    Order getOrderDetails(String id) throws SQLException, ClassNotFoundException;
    boolean updateOrderStatus(String id, String status) throws SQLException, ClassNotFoundException;
}
