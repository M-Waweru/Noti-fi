/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedule;

import databaseconnect.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Mathenge
 */
public class ScheduleCreation {

    private String scheduledate;
    private int notificationNo;
    private String scheduletime;

    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public ScheduleCreation(int notifino, String date) {
        this.scheduledate = date;
        this.notificationNo = notifino;
    }

    public boolean saveSchedule() {
        ConnectionManager conman = new ConnectionManager();
        conn = conman.getConnection();

        try {
            stmt = conn.prepareStatement("INSERT INTO `schedule`(`Schedule No`, `Notification No`, `Time`, `Date`) "
                    + "VALUES (?,?,?,?)");
            stmt.setString(1, null);
            stmt.setInt(2, this.notificationNo);
            stmt.setString(3, null);
            stmt.setString(4, this.scheduledate);

            int rowsinserted = stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            return false;
        }
    }

}
