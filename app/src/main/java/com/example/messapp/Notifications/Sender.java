package com.example.messapp.Notifications;

public class Sender {
    public Notification notification;
    public String to;

    public Sender(Notification notification, String to){
        this.notification = notification;
        this.to = to;
    }
}
