/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnect;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author mathe
 */
public class ConnectionManager {

    private static String url = "jdbc:mysql://localhost:3306/notifi_db";
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String username = null;
    private static String password = null;
    private static Connection con;

    public Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                getDbCredentials();
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found." + ex);
        }
        System.out.println("Success");
        return con;
    }

    private void getDbCredentials() throws IOException {
        Properties prop = new Properties();
        String propFileName = "config" + File.separator + "dbconfig.properties";

        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try (InputStream resourceStream = loader.getResourceAsStream(propFileName)) {
            prop.load(resourceStream);
            username = prop.getProperty("username");
            password = prop.getProperty("password");
        }
    }
}