package lk.ijse.fuelBee.dao.custom.impl;

import lk.ijse.fuelBee.dao.SQLUtil;
import lk.ijse.fuelBee.dao.custom.OrderDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lk.ijse.fuelBee.dto.EmployeeDto;
import lk.ijse.fuelBee.dto.MachineDto;
import lk.ijse.fuelBee.entity.Order;

public class OrderDAOImpl implements OrderDAO {
   @Override
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute("INSERT INTO Orders VALUES(?,?,?,?,?,?,?)",entity.getOrderId(),entity.getEmail(), entity.getType(),entity.getDate(), entity.getTankQty(), entity.getPrice(), entity.getStatus());

    }
   @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       return  SQLUtil.execute("DELETE FROM Orders WHERE order_id=?", id);

    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Order get(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("UPDATE Orders SET email=?,type=?,date=?,tank_qty=?,price=?,status=? WHERE order_id=?", entity.getEmail(), entity.getType(), entity.getDate(), entity.getTankQty(), entity.getPrice(), entity.getStatus(), entity.getOrderId());
    }
   @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
       ResultSet rs = SQLUtil.execute("SELECT * FROM Orders");
        ArrayList<Order> orders = new ArrayList<>();

        while(rs.next()){
            orders.add(new Order(
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
    public Order getOrderDetails(String id) throws SQLException, ClassNotFoundException {
       ResultSet rs  = SQLUtil.execute("SELECT * FROM Orders WHERE order_id=?", id);
        Order order = new Order();
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
        return SQLUtil.execute("UPDATE Orders SET status='PAID' WHERE order_id=?",(id));
    }
}
