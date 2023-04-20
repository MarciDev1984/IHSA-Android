package com.example.ihsastable.data.model;

import com.google.firebase.Timestamp;

public class Announcement {
    private String Message;
    private Timestamp TimePublished;
    public Announcement(){}

    public Announcement(final String message, final Timestamp timePublished) {
        this.Message = message;
        this.TimePublished = timePublished;
    }

    public String getMessage() {
        return this.Message;
    }

    public Timestamp getTimePublished() {
        return this.TimePublished;
    }
}
