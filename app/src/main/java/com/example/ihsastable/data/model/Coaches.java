package com.example.ihsastable.data.model;

import java.util.ArrayList;

public class Coaches {
    public ArrayList<Coach> coaches;
    private static Coaches theModel;
    public static Coaches getModel(){
        if (Coaches.theModel == null){
            Coaches.theModel = new Coaches();
        }
        return Coaches.theModel;
    }
    private Coaches(){
        coaches = new ArrayList<>();
    }
}
