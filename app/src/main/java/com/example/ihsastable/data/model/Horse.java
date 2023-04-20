package com.example.ihsastable.data.model;

public class Horse {
    private int Id;
    private String Name;
    private String Description;
    private String Provider;
    public Horse() {
    }

    public Horse(final int id, final String name, final String description, final String provider) {
        Id = id;
        this.Name = name;
        this.Description = description;
        this.Provider = provider;
    }

    public int getId() {
        return this.Id;
    }

    public String getName() {
        return this.Name;
    }

    public String getDescription() {
        return this.Description;
    }

    public String getProvider() {
        return this.Provider;
    }


}