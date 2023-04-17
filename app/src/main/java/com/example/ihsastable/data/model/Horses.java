package com.example.ihsastable.data.model;

import java.util.ArrayList;

public class Horses {
    public ArrayList<Horse> horses;
    private static Horses theModel;
    public static Horses getModel(){
        if (theModel == null){
            theModel = new Horses();
        }
        return theModel;
    }
    private Horses(){
        this.horses= new ArrayList<>();
    }
}
