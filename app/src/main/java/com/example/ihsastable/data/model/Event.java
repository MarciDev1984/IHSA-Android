package com.example.ihsastable.data.model;

import com.google.firebase.Timestamp;
import com.google.type.DateTime;

import java.util.List;

public class Event {
    private String Location;
    //Firebase uses Timestamp to store dates, like from Date()
    private Timestamp EventTime;
    private List<Rider> Riders;
    private int Zone;

    public Event(){}

    public Event(String location, Timestamp eventTime, List<Rider> riders, int zone) {
        Location = location;
        EventTime = eventTime;
        Riders = riders;
        Zone = zone;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Timestamp getEventTime() {
        return EventTime;
    }

    public void setEventTime(Timestamp eventTime) {
        EventTime = eventTime;
    }

    public List<Rider> getRiders() {
        return Riders;
    }

    public void setRiders(List<Rider> riders) {
        Riders = riders;
    }

    public int getZone() {
        return Zone;
    }

    public void setZone(int zone) {
        Zone = zone;
    }
}
