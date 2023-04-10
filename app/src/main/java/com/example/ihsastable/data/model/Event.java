package com.example.ihsastable.data.model;

import com.google.firebase.Timestamp;

import java.util.List;

public class Event {
    private int Id;
    private String Location;
    private Timestamp EventTime;
    private List<Integer> Riders;   //contains rider id's, this can be used to get the riders for the event
    private int Zone;

    public Event() {
    }

    public Event(int id, String location, Timestamp eventTime, List<Integer> riders, int zone) {
        Id = id;
        Location = location;
        EventTime = eventTime;
        Riders = riders;
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

    public List<Integer> getRiders() {
        return Riders;
    }

    public int getZone() {
        return Zone;
    }
}
