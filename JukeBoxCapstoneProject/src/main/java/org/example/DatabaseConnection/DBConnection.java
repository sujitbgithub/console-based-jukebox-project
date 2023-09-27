package org.example.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements ConnectiontoDB {
    public static java.sql.Connection con;

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //System.out.println("Driver Loaded");

        {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukeboxfinal?useSSL=false", "root", "root");
            //System.out.println("Connection Established");
        }


        return con;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DBConnection db=new DBConnection();
        db.getConnection();
    }
}
