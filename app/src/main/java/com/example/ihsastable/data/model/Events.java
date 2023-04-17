package com.example.ihsastable.data.model;

import java.util.ArrayList;

public class Events {

    public ArrayList<Event> events;
    private static Events theModel;
    public static Events getModel(){
        if (theModel == null){
            theModel = new Events();
        }
        return theModel;
    }
    private Events(){
        this.events = new ArrayList<>();
    }
}
