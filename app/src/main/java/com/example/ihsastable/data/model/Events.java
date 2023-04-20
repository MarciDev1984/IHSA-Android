package com.example.ihsastable.data.model;

import java.util.ArrayList;

public class Events {

    public ArrayList<Event> events;
    private static Events theModel;
    public static Events getModel(){
        if (Events.theModel == null){
            Events.theModel = new Events();
        }
        return Events.theModel;
    }
    private Events(){
        events = new ArrayList<>();
    }
}
