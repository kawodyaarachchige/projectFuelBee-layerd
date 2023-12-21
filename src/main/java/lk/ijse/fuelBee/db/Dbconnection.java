package lk.ijse.fuelBee.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {
    private static Dbconnection dbconnection;
    private Connection connection;
    private Dbconnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/FuelBee","root","Ijse@1234");
    }

    public static Dbconnection getInstance() throws SQLException {
        return (null==dbconnection) ? dbconnection = new Dbconnection(): dbconnection;
    }
    public Connection getConnection(){
        return connection;
    }
}

