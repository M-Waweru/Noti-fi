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

    private String subject, content, datecreated;
    private int adminno, notification_type;

    public Notification(String subject, String content, int adminno, int type) {
        this.subject = subject;
        this.content = content;
        this.adminno = adminno;
        this.notification_type = type;
        this.datecreated = generateDateCreated();
        
        NotificationCreation createnote = new NotificationCreation(subject, content, adminno, type, datecreated);
    }
    
    public static void main(String[] args) {
        new Notification("Hello", "Hello", 1, 1);
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
}
