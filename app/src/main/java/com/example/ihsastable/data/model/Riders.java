package com.example.ihsastable.data.model;

import java.util.ArrayList;

public class Riders {

    public ArrayList<Rider> riders;
    private static Riders theModel;
    public static Riders getModel(){
        if (theModel == null){
            theModel = new Riders();
        }
        return theModel;
    }
    private Riders(){
        this.riders = new ArrayList<>();
    }
}
