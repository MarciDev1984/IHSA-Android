package com.example.ihsastable.data.model;

import com.google.firebase.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {
    private int Id;
    private String EventName;
    private String Location;
    private Date EventTime;
    private ArrayList<Integer> EventClasses;
    private int Zone;

    public Event() {
    }

    public ArrayList<Integer> getEventClasses() {
        return this.EventClasses;
    }

    public Event(final int id, final String eventName, final String location, final Date eventTime, final ArrayList<Integer> eventClasses, final int zone) {
        this.Id = id;
        this.EventName = eventName;
        this.Location = location;
        this.EventTime = eventTime;
        this.EventClasses = eventClasses;
        this.Zone = zone;
    }

    public int getId() {
        return this.Id;
    }

    public String getLocation() {
        return this.Location;
    }

    public Date getEventTime() {
        return this.EventTime;
    }


    public int getZone() {
        return this.Zone;
    }

    public String getEventName() {
        return this.EventName;
    }
}
