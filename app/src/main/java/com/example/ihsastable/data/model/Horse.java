package com.example.ihsastable.data.model;

public class Horse {
    private int id;
    private String Name;
    private String Description;
    private String Provider;

    public Horse() {
    }

    public Horse(int id, String name, String description, String provider) {
        this.id = id;
        Name = name;
        Description = description;
        Provider = provider;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getProvider() {
        return Provider;
    }


}