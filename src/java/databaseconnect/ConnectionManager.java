/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnect;
import java.sql.*;
import sun.applet.Main;
/**
 *
 * @author mathe
 */
public class ConnectionManager {

    private static String url = "jdbc:mysql://localhost:3306/notifi_db";
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String username = "root";
    private static String password = "";
    private static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found.");
        }
        System.out.println("Success");
        return con;
    }
    
    public static void main(String[] args) {
        ConnectionManager conman = new ConnectionManager();
        conman.getConnection();
    }
}
