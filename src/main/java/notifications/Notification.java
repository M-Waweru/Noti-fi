/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notifications;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author mathe
 */
public class Notification implements Serializable {

    private String subject, content, datecreated, imagedir;
    private int adminno, type, notificationno;

    public Notification(String subject, String content, int adminno, int type, String imagedir) {
        this.subject = subject;
        this.content = content;
        this.adminno = adminno;
        this.type = type;
        this.datecreated = generateDateCreated();
        this.imagedir = imagedir;
    }

    public int saveNotification() {
        NotificationCreation createnote = new NotificationCreation(subject, content, adminno, type, datecreated, imagedir);
        return createnote.getNotificationNo();
    }

    public int getNotificationno() {
        return notificationno;
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
        return type;
    }

    public String getDatecreated() {
        return datecreated;
    }

    private String generateDateCreated() {
        LocalDateTime ldt = LocalDateTime.now();
        return ldt.toString();
    }
}
