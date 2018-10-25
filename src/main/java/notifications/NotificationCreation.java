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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mathe
 */
public class NotificationCreation {

    private Connection conn = null;
    private PreparedStatement prstmt = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public NotificationCreation(String subject, String content, int adminno, int type, String datecreated, String imagedir) {
        ConnectionManager conman = new ConnectionManager();
        conn = conman.getConnection();

        try {
            prstmt = conn.prepareStatement("INSERT INTO `notifications`(`Notification No`, `Notification Subject`, "
                    + "`Notification Content`, `Admin No`, `Notification Type No`, `Date created`, `Image dir`)"
                    + " VALUES (?,?,?,?,?,?,?)");
            prstmt.setString(1, null);
            prstmt.setString(2, subject);
            prstmt.setString(3, content);
            prstmt.setInt(4, adminno);
            prstmt.setInt(5, type);
            prstmt.setString(6, datecreated);
            prstmt.setString(7, imagedir);

            int rowsinserted = prstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    public int getNotificationNo() {
        try {
            ConnectionManager conman = new ConnectionManager();
            conn = conman.getConnection();
            int notifino = -1;
            stmt = conn.createStatement();
            String sql = "SELECT * FROM `notifications` WHERE `Notification No` = (SELECT MAX(`Notification No`) FROM `notifications`)";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                notifino = rs.getInt(1);
            }

            return notifino;
        } catch (SQLException ex) {
            Logger.getLogger(NotificationCreation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
