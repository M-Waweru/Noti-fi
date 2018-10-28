/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnect;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                getPropValues();
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection.");
            } catch (IOException ex) {
                Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found." + ex);
        }
        System.out.println("Success");
        return con;
    }

    private void getPropValues() throws IOException {
        InputStream inputStream = null;

        try {
            Properties prop = new Properties();
            String propFileName = "config/dbconfig.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            username = prop.getProperty("username");
            password = prop.getProperty("password");
            System.out.println(""+username);
            System.out.println(""+password);
        } finally {
            inputStream.close();
        }
    }

    public static void main(String[] args) {
        ConnectionManager conman = new ConnectionManager();
        conman.getConnection();
    }
}
