package com.example.ihsastable.data.model;

import com.google.firebase.Timestamp;

public class Announcement {
    private String Message;
    private Timestamp TimePublished;
    public Announcement(){}

    public Announcement(String message, Timestamp timePublished) {
        Message = message;
        TimePublished = timePublished;
    }

    public String getMessage() {
        return Message;
    }

    public Timestamp getTimePublished() {
        return TimePublished;
    }
}
