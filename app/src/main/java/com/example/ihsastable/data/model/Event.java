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
        return EventClasses;
    }

    public Event(int id, String eventName, String location, Date eventTime, ArrayList<Integer> eventClasses, int zone) {
        Id = id;
        EventName = eventName;
        Location = location;
        EventTime = eventTime;
        EventClasses = eventClasses;
        Zone = zone;
    }

    public int getId() {
        return Id;
    }

    public String getLocation() {
        return Location;
    }

    public Date getEventTime() {
        return EventTime;
    }


    public int getZone() {
        return Zone;
    }

    public String getEventName() {
        return EventName;
    }
}
