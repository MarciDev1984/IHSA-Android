package com.example.ihsastable.data.model;

import java.util.ArrayList;

public class Coaches {
    public ArrayList<Coach> coaches;
    private static Coaches theModel;
    public static Coaches getModel(){
        if (theModel == null){
            theModel = new Coaches();
        }
        return theModel;
    }
    private Coaches(){
        this.coaches = new ArrayList<>();
    }
}
