package com.example.ihsastable.data.model;

import java.util.ArrayList;
import java.util.List;

public class Rider extends User {
    private int riderId;
    private boolean isHeightWeightRider;
    private double height;
    private double weight;

    public Rider(String Username, String FirstName, String LastName, int riderId, boolean isHeightWeightRider, double height, double weight, List<Coach> managedBy, String playsFor) {
        super(Username, FirstName, LastName);
        this.riderId = riderId;
        this.isHeightWeightRider = isHeightWeightRider;
        this.height = height;
        this.weight = weight;
        this.managedBy = managedBy;
        this.playsFor = playsFor;
    }

    //Firebase needs a List object, cannot handle ArrayList
    private List<Coach> managedBy = new ArrayList<>();
    private String playsFor;
    public Rider(){}

    public boolean isHeightWeightRider() {
        return isHeightWeightRider;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public List<Coach> getManagedBy() {
        return managedBy;
    }

    public String getPlaysFor() {
        return playsFor;
    }
}
