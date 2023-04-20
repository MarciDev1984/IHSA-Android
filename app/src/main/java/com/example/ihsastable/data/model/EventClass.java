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

    public EventClass(final int id, final String className, final String pattern, final ArrayList<Integer> riders, final ArrayList<Integer> horses) {
        this.Id = id;
        this.ClassName = className;
        this.Pattern = pattern;
        this.Riders = riders;
        this.Horses = horses;
    }

    public int getId() {
        return this.Id;
    }

    public String getClassName() {
        return this.ClassName;
    }

    public String getPattern() {
        return this.Pattern;
    }

    public ArrayList<Integer> getRiders() {
        return this.Riders;
    }

    public ArrayList<Integer> getHorses() {
        return this.Horses;
    }



    public EventClass(){}
}
