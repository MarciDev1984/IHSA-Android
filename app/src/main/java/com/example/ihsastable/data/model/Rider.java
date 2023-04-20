package com.example.ihsastable.data.model;

import java.util.ArrayList;
import java.util.List;

public class Rider extends User {
    private int RiderId;
    private int Points;
    private int Position;
    private double AveragePointsPerRide;
    // private int ManagedBy; //Uses CoachId
    // private int Class;  // Uses Class id
    private int PlaysFor; // Uses SchoolId

    public Rider() {
    }

    public Rider(final int Id, final String Username, final String FirstName, final String LastName, final int riderId, final int points, final int position, final double averagePointsPerRide, final int playsFor) {
        super(Id, Username, FirstName, LastName);
        this.RiderId = riderId;
        this.Points = points;
        this.Position = position;
        this.AveragePointsPerRide = averagePointsPerRide;
        this.PlaysFor = playsFor;
    }

    public int getRiderId() {
        return this.RiderId;
    }

    public int getPoints() {
        return this.Points;
    }

    public int getPosition() {
        return this.Position;
    }

    public double getAveragePointsPerRide() {
        return this.AveragePointsPerRide;
    }


    public int getPlaysFor() {
        return this.PlaysFor;
    }
}
