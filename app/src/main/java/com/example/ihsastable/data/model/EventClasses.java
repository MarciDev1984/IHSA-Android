package com.example.ihsastable.data.model;

import java.util.HashMap;
import java.util.List;

public class EventClasses {
    public HashMap<Event, List<EventClass>> eventClasses;
    private static EventClasses theModel;
    public static EventClasses getModel(){
        if (theModel == null){
            theModel = new EventClasses();
        }
        return theModel;
    }
    private EventClasses(){
        this.eventClasses = new HashMap<>();
    }
}
