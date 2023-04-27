package com.example.ihsastable.data.model;


import java.util.ArrayList;
import java.util.List;

// to be finished later...
public class EventClass{
    private int Id;
    private String ClassName;
    private String Pattern;
    private ArrayList<Integer> Riders;
    private ArrayList<Integer> Horses;

    public EventClass(int id, String className, String pattern, ArrayList<Integer> riders, ArrayList<Integer> horses) {
        Id = id;
        ClassName = className;
        Pattern = pattern;
        Riders = riders;
        Horses = horses;
    }

    public int getId() {
        return Id;
    }

    public String getClassName() {
        return ClassName;
    }

    public String getPattern() {
        return Pattern;
    }

    public ArrayList<Integer> getRiders() {
        return Riders;
    }

    public ArrayList<Integer> getHorses() {
        return Horses;
    }



    public EventClass(){}
}
