/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notifications;

import java.sql.*;
import java.time.*;
import databaseconnect.ConnectionManager;
import java.util.*;
import java.io.*;

/**
 *
 * @author mathe
 */
public class NotificationCreation {

    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public NotificationCreation(String subject, String content, int adminno, int type, String datecreated) {
        ConnectionManager conman = new ConnectionManager();
        conn = conman.getConnection();

        try {
            stmt = conn.prepareStatement("INSERT INTO `notifications`(`Notification No`, `Notification Subject`, "
                    + "`Notification Content`, `Admin No`, `Notification Type No`, `Date created`)"
                    + " VALUES (?,?,?,?,?,?)");
            stmt.setString(1, null);
            stmt.setString(2, subject);
            stmt.setString(3, content);
            stmt.setInt(4, adminno);
            stmt.setInt(5, type);
            stmt.setString(6, datecreated);

            int rowsinserted = stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }
}
