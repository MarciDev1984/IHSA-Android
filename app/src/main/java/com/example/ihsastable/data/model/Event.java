package com.example.ihsastable.data.model;

import com.google.firebase.Timestamp;

import java.util.List;

public class Event {
    private int Id;
    private String Location;
    private Timestamp EventTime;
    private List<Integer> Classes;
    private int Zone;

    public Event() {
    }

    public List<Integer> getClasses() {
        return Classes;
    }

    public Event(int id, String location, Timestamp eventTime, List<Integer> classes, int zone) {
        Id = id;
        Location = location;
        EventTime = eventTime;
        Classes = classes;
        Zone = zone;
    }

    public int getId() {
        return Id;
    }

    public String getLocation() {
        return Location;
    }

    public Timestamp getEventTime() {
        return EventTime;
    }


    public int getZone() {
        return Zone;
    }
}
