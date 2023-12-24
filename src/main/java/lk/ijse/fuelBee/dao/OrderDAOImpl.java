package lk.ijse.fuelBee.dao;

import lk.ijse.fuelBee.db.Dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lk.ijse.fuelBee.dto.OrderDto;

public class OrderDAOImpl {
    public boolean saveOrder(OrderDto orderDto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();

        String sql = "INSERT INTO Orders VALUES(?,?,?,?,?,?,?) ";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, orderDto.getOrderId());
        pstm.setString(2, orderDto.getEmail());
        pstm.setString(3, orderDto.getType());
        pstm.setDate(4, orderDto.getDate());
        pstm.setInt(5, orderDto.getTankQty());
        pstm.setDouble(6, orderDto.getPrice());
        pstm.setString(7, orderDto.getStatus());


        if (pstm.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }
    public boolean deleteOrder(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql="DELETE FROM Orders WHERE order_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);

        if(pstm.executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }
    public boolean updateOrder(OrderDto orderDto) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql="UPDATE Orders SET email=?,type=?,date=?,tank_qty=?,price=?,status=? WHERE order_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, orderDto.getEmail());
        pstm.setString(2, orderDto.getType());
        pstm.setDate(3, orderDto.getDate());
        pstm.setInt(4, orderDto.getTankQty());
        pstm.setDouble(5, orderDto.getPrice());
        pstm.setString(6, orderDto.getStatus());
        pstm.setString(7, orderDto.getOrderId());
        if(pstm.executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }
    public ArrayList<OrderDto> getAllOrders() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql="select * from Orders";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs=pstm.executeQuery();
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
    public int getOrderCount() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql="select count(*) from Orders";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs=pstm.executeQuery();

        while(rs.next()){
            return rs.getInt(1);
        }return 0;

    }
    public OrderDto getOrderDetails(String id) throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();
        String sql="select * from Orders where order_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet rs=pstm.executeQuery();
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
