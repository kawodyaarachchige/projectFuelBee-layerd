package lk.ijse.fuelBee.dao.impl;

import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.OrderDAO;
import lk.ijse.fuelBee.db.Dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lk.ijse.fuelBee.dto.OrderDto;

public class OrderDAOImpl implements OrderDAO {
   @Override
    public boolean save(OrderDto orderDto) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute("INSERT INTO Orders VALUES(?,?,?,?,?,?,?)", orderDto.getOrderId(), orderDto.getEmail(), orderDto.getType(), orderDto.getDate(), orderDto.getTankQty(), orderDto.getPrice(), orderDto.getStatus());
    }
   @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       return  SQLUtil.execute("DELETE FROM Orders WHERE order_id=?", id);

    }

    @Override
    public OrderDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderDto get(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean update(OrderDto orderDto) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("UPDATE Orders SET email=?,type=?,date=?,tank_qty=?,price=?,status=? WHERE order_id=?", orderDto.getEmail(), orderDto.getType(), orderDto.getDate(), orderDto.getTankQty(), orderDto.getPrice(), orderDto.getStatus(), orderDto.getOrderId());
    }
   @Override
    public ArrayList<OrderDto> getAll() throws SQLException, ClassNotFoundException {
       ResultSet rs = SQLUtil.execute("SELECT * FROM Orders");
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
        }return orders;
    }
   @Override
    public int getOrderCount() throws SQLException, ClassNotFoundException {
       ResultSet rs = SQLUtil.execute("SELECT count(*) FROM Orders");
        while(rs.next()){
            return rs.getInt(1);
        }return 0;

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
}
