package com.example.ihsastable.data.model;


import java.util.List;

// to be finished later...
public class EventClass{
    ;
    private int Id;

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

    private String ClassName;   // should likely get class name from enum
    private String Pattern; // should get from Pattern enum
    private List<Integer> Riders;
    private List<Integer> Horses;

    public EventClass(){}
}
