package com.example.ihsastable.data.model;


import java.util.List;

// to be finished later...
public class EventClass{
    private int Id;
    private String ClassName;
    private String Pattern;
    private List<Integer> Riders;
    private List<Integer> Horses;

    public EventClass(int id, String className, String pattern, List<Integer> riders, List<Integer> horses) {
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

    public List<Integer> getRiders() {
        return Riders;
    }

    public List<Integer> getHorses() {
        return Horses;
    }



    public EventClass(){}
}
