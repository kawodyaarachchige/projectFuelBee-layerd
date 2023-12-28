package lk.ijse.fuelBee.bo.custom.impl;

import lk.ijse.fuelBee.bo.SuperBO;
import lk.ijse.fuelBee.bo.custom.OrderBO;
import lk.ijse.fuelBee.dao.DAOFactory;
import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.OrderDAO;
import lk.ijse.fuelBee.dao.custom.PaymentsDAO;
import lk.ijse.fuelBee.dto.AdminDto;
import lk.ijse.fuelBee.dto.OrderDto;
import lk.ijse.fuelBee.entity.Order;
import lk.ijse.fuelBee.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER);

   // PaymentsDAO paymentsDAO = (PaymentsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.PAYMENTS);
   @Override
    public boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
       // return  SQLUtil.execute("INSERT INTO Orders VALUES(?,?,?,?,?,?,?)",dto.getOrderId(),dto.getEmail(), dto.getType(),dto.getDate(), dto.getTankQty(), dto.getPrice(), dto.getStatus());
        return orderDAO.save(new Order(dto.getOrderId(),dto.getEmail(), dto.getType(),dto.getDate(), dto.getTankQty(), dto.getPrice(), dto.getStatus()));
    }
   @Override
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
      // return  SQLUtil.execute("DELETE FROM Orders WHERE order_id=?", id);
    return  orderDAO.delete(id);
    }

    @Override
    public boolean updateOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
     return  orderDAO.update(new Order(dto.getOrderId(),dto.getEmail(), dto.getType(),dto.getDate(), dto.getTankQty(), dto.getPrice(), dto.getStatus()));
       //  return SQLUtil.execute("UPDATE Orders SET email=?,type=?,date=?,tank_qty=?,price=?,status=? WHERE order_id=?", dto.getEmail(), dto.getType(), dto.getDate(), dto.getTankQty(), dto.getPrice(), dto.getStatus(), dto.getOrderId());
      //  return SQLUtil.execute("UPDATE Orders SET status='PAID' WHERE order_id=?",(new Payment(paymentDto) .getOrderId()));
    }


    @Override
    public ArrayList<OrderDto> getAllOrder() throws SQLException, ClassNotFoundException {
       ArrayList<Order> orders = orderDAO.getAll();
       ArrayList<OrderDto> orderDtos = new ArrayList<>();
       for (Order order : orders) {
           orderDtos.add(new OrderDto(order.getOrderId(),order.getEmail(), order.getType(),order.getDate(), order.getTankQty(), order.getPrice(), order.getStatus()));
       }
       return orderDtos;
       /*ResultSet rs = SQLUtil.execute("SELECT * FROM Orders");
        ArrayList<OrderDto> orders = new ArrayList<>();

        while(rs.next()){
            orders.add(new OrderDto(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDate(4),
                    rs.getInt(5),
                    rs.getDouble(6),
                    rs.getString(7)
            ));
        }return orders;*/
    }
   @Override
    public int getOrderCount() throws SQLException, ClassNotFoundException {
       return  orderDAO.getOrderCount();
      /* ResultSet rs = SQLUtil.execute("SELECT count(*) FROM Orders");
        while(rs.next()){
            return rs.getInt(1);
        }return 0;
*/
    }
   @Override
    public OrderDto getOrderDetails(String id) throws SQLException, ClassNotFoundException {
       ResultSet rs  = SQLUtil.execute("SELECT * FROM Orders WHERE order_id=?", id);
        OrderDto order = new OrderDto();
        while(rs.next()){
            order.setOrderId(rs.getString(1));
            order.setEmail(rs.getString(2));
            order.setType(rs.getString(3));
            order.setDate(rs.getDate(4));
            order.setTankQty(rs.getInt(5));
            order.setPrice(rs.getDouble(6));
            order.setStatus(rs.getString(7));
        }
        return order;
    }

    @Override
    public boolean updateOrderStatus(String id, String status) throws SQLException, ClassNotFoundException {
       // return SQLUtil.execute("UPDATE Orders SET status=? WHERE order_id=?", status, id);
      return orderDAO.updateStatus(id, status);
        // return SQLUtil.execute("UPDATE Orders SET status='PAID' WHERE order_id=?",(new Payment() .getOrderId()) );
    }
}
