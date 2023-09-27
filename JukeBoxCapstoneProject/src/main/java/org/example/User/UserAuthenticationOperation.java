package org.example.User;

import java.sql.*;
import java.util.Scanner;

public class UserAuthenticationOperation {
    Scanner sc = new Scanner(System.in);
    public static UserAuthentication ob = new UserAuthentication();

    Connection con;

    public Connection getCon() throws SQLException {
        con= DriverManager.getConnection("jdbc:mysql://localhost:3306/user_authentication?useSSL=false", "root", "root");
        return con;
    }

//    public void RegisterUser() throws SQLException {
//        System.out.println("Enter User Id: ");
//        int id = sc.nextInt();
//        System.out.println("Enter password: ");
//        String password = sc.next();
//
//        ob.setUser_id(id);
//        ob.setUser_password(password);
//
//        String sql = "insert into users (user_id, user_password) values (?, ?)  ON DUPLICATE KEY UPDATE user_password=?";
//        PreparedStatement stmt = getCon().prepareStatement(sql);
//        stmt.setInt(1, ob.getUser_id());
//        stmt.setString(2, ob.getUser_password());
//
//        int rowsInserted = stmt.executeUpdate();
//        if (rowsInserted > 0) {
//            System.out.println("A new user was inserted successfully!");
//        }
//    }
public void RegisterUser() {
    System.out.println("Enter User Id: ");
    int id = sc.nextInt();
    System.out.println("Enter password: ");
    String password = sc.next();

    ob.setUser_id(id);
    ob.setUser_password(password);

    String sql = "insert into users (user_id, user_password) values (" + ob.getUser_id() + ", '" + ob.getUser_password() + "')";
    try (Connection con = getCon();
         Statement stmt = con.createStatement()) {
        int rowsInserted = stmt.executeUpdate(sql);
        if (rowsInserted > 0) {
            System.out.println("User registered successfully!");
        }
    } catch (SQLIntegrityConstraintViolationException e) {
        System.out.println("User ID already exists. Please try Logging In");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public boolean loginUser() {
        System.out.println("****************************************");
        System.out.println("****************LOGIN*******************");
        System.out.println("****************************************");
        System.out.println("Enter User ID: ");
        int userID = sc.nextInt();
        System.out.println("Enter Password: ");
        String password = sc.next();
        ob.setUser_id(userID);
        ob.setUser_password(password);
        try {
            con = getCon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String pwd = rs.getString("user_password");
                if (ob.getUser_id() == id && ob.getUser_password().equals(pwd)) {
                    System.out.println("Login Successful");
                   // System.out.println(ob.getUser_id()+" "+ob.getUser_password());
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Login Failed");
        return false;
    }

    public static void main(String[] args) throws SQLException {
        UserAuthenticationOperation u=new UserAuthenticationOperation();
        u.loginUser();
        System.out.println(ob.getUser_id()+" "+ob.getUser_password());

    }




}
