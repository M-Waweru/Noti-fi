/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notifications;

import java.time.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author mathe
 */
public class Notification implements Serializable{

    private String notificationNo;
    private String subject;
    private String content;
    private int adminno;
    private int notification_type;
    private String datecreated;

    public Notification(String subject, String content, int adminno, int type) {
        this.notificationNo = generateNotificationNo();
        this.subject = subject;
        this.content = content;
        this.adminno = adminno;
        this.notification_type = type;
        this.datecreated = generateDateCreated();
        
        NotificationCreation createnote = new NotificationCreation(notificationNo, subject, content, adminno, type, datecreated);
    }

    public String getNotificationNo() {
        return notificationNo;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public int getAdminno() {
        return adminno;
    }

    public int getNotification_type() {
        return notification_type;
    }

    public String getDatecreated() {
        return datecreated;
    }

    public String generateDateCreated() {
        LocalDateTime ldt = LocalDateTime.now();
        return ldt.toString();
    }

    public String generateNotificationNo() {
        UUID notifno = UUID.randomUUID();
        return notifno.toString();
    }
}
